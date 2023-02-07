package com.fearlessspider.god.components;

import android.app.AlarmManager;
import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.IBinder;

import com.fearlessspider.god.BuildConfig;
import com.fearlessspider.god.MainActivity;
import com.fearlessspider.god.R;
import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.repository.StepRepository;
import com.fearlessspider.god.utils.API26Wrapper;
import com.fearlessspider.god.utils.DateUtil;
import com.fearlessspider.god.utils.Logger;
import com.fearlessspider.god.widget.WidgetUpdateService;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class SensorListener extends Service implements SensorEventListener {

    public final static int NOTIFICATION_ID = 1;
    private final static long MICROSECONDS_IN_ONE_MINUTE = 60000000;
    private final static long SAVE_OFFSET_TIME = AlarmManager.INTERVAL_HOUR;
    private final static int SAVE_OFFSET_STEPS = 500;

    private static int steps;
    private static int lastSaveSteps = 0;
    private static long lastSaveTime;

    private final BroadcastReceiver shutdownReceiver = new ShutdownReceiver();

    private StepRepository stepRepository;

    @Override
    public void onAccuracyChanged(final Sensor sensor, int accuracy) {
        // nobody knows what happens here: step value might magically decrease
        // when this method is called...
        if (BuildConfig.DEBUG) Logger.log(sensor.getName() + " accuracy changed: " + accuracy);
    }

    @Override
    public void onSensorChanged(final SensorEvent event) {
        if (event.values[0] > Integer.MAX_VALUE) {
            if (BuildConfig.DEBUG) Logger.log("probably not a real value: " + event.values[0]);
            return;
        } else {
            steps = (int) event.values[0];
            updateIfNecessary();
        }
    }

    /**
     * @return true, if notification was updated
     */
    private boolean updateIfNecessary() {
        if (steps > lastSaveSteps + SAVE_OFFSET_STEPS ||
                (steps > 0 && System.currentTimeMillis() > lastSaveTime + SAVE_OFFSET_TIME)) {
            if (BuildConfig.DEBUG) Logger.log(
                    "saving steps: steps=" + steps + " lastSave=" + lastSaveSteps +
                            " lastSaveTime=" + new Date(lastSaveTime));
            stepRepository = new StepRepository((Application) this.getApplicationContext());
            if (stepRepository.getCurrentStepsCount() == null) {
                int pauseDifference = steps -
                        getSharedPreferences("G.O.D.", Context.MODE_PRIVATE)
                                .getInt("pauseCount", steps);
                if(BuildConfig.DEBUG) Logger.log("Sensor New Day");
                stepRepository.insert(0);
                if (pauseDifference > 0) {
                    if(BuildConfig.DEBUG) Logger.log("Sensor " + steps);
                    // update pauseCount for the new day
                    getSharedPreferences("G.O.D.", Context.MODE_PRIVATE).edit()
                            .putInt("pauseCount", steps).commit();
                }
            }
            Step step = stepRepository.getCurrentStep();
            if (step == null) {
                int pauseDifference = steps -
                        getSharedPreferences("G.O.D.", Context.MODE_PRIVATE)
                                .getInt("pauseCount", steps);
                stepRepository.insert(pauseDifference);
                lastSaveSteps = pauseDifference;
            } else {
                int pauseDifference = steps -
                        getSharedPreferences("G.O.D.", Context.MODE_PRIVATE)
                                .getInt("pauseCount", steps);
                step.setSteps(pauseDifference);
                stepRepository.update(step);
                lastSaveSteps = pauseDifference;
            }
            lastSaveTime = System.currentTimeMillis();
            showNotification(); // update notification
            WidgetUpdateService.enqueueUpdate(this);
            return true;
        } else {
            return false;
        }
    }

    private void showNotification() {
        if (Build.VERSION.SDK_INT >= 26) {
            startForeground(NOTIFICATION_ID, getNotification(this));
        } else if (getSharedPreferences("G.O.D.", Context.MODE_PRIVATE)
                .getBoolean("notification", true)) {
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE))
                    .notify(NOTIFICATION_ID, getNotification(this));
        }
    }

    @Override
    public IBinder onBind(final Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        reRegisterSensor();
        registerBroadcastReceiver();
        if (!updateIfNecessary()) {
            showNotification();
        }

        // restart service every hour to save the current step count
        long nextUpdate = Math.min(DateUtil.getTomorrow(),
                System.currentTimeMillis() + AlarmManager.INTERVAL_HOUR);
        if (BuildConfig.DEBUG) Logger.log("next update: " + new Date(nextUpdate).toLocaleString());
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) Logger.log("SensorListener onCreate");
    }

    @Override
    public void onTaskRemoved(final Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        if (BuildConfig.DEBUG) Logger.log("sensor service task removed");
        // Restart service in 500 ms
        ((AlarmManager) getSystemService(Context.ALARM_SERVICE))
                .set(AlarmManager.RTC, System.currentTimeMillis() + 500, PendingIntent
                        .getService(this, 3, new Intent(this, SensorListener.class), PendingIntent.FLAG_IMMUTABLE));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) Logger.log("SensorListener onDestroy");
        try {
            SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
            sm.unregisterListener(this);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) Logger.log(e);
            e.printStackTrace();
        }
    }

    public static Notification getNotification(final Context context) {
        if (BuildConfig.DEBUG) Logger.log("getNotification");
        SharedPreferences prefs = context.getSharedPreferences("G.O.D.", Context.MODE_PRIVATE);
        int goal = prefs.getInt("goal", 10000);

        Integer today_offset = -prefs.getInt("pauseCount", 0);
        Notification.Builder notificationBuilder =
                Build.VERSION.SDK_INT >= 26 ? API26Wrapper.getNotificationBuilder(context) :
                        new Notification.Builder(context);
        if (steps > 0) {
            NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
            notificationBuilder.setProgress(goal, today_offset + steps, false).setContentText(
                    today_offset + steps >= goal ?
                            context.getString(R.string.goal_reached_notification,
                                    format.format((today_offset + steps))) :
                            context.getString(R.string.notification_text,
                                    format.format((goal - today_offset - steps)))).setContentTitle(
                    format.format(today_offset + steps) + " " + context.getString(R.string.steps));
        } else { // still no step value?
            notificationBuilder.setContentText(
                            context.getString(R.string.your_progress_will_be_shown_here_soon))
                    .setContentTitle(context.getString(R.string.notification_title));
        }
        notificationBuilder.setPriority(Notification.PRIORITY_MIN).setShowWhen(false)
                .setContentIntent(PendingIntent
                        .getActivity(context, 0, new Intent(context, MainActivity.class),
                                PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT))
                .setSmallIcon(R.drawable.ic_notifications_black_24dp).setOngoing(true);
        return notificationBuilder.build();
    }

    private void registerBroadcastReceiver() {
        if (BuildConfig.DEBUG) Logger.log("register broadcastreceiver");
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SHUTDOWN);
        registerReceiver(shutdownReceiver, filter);
    }

    private void reRegisterSensor() {
        if (BuildConfig.DEBUG) Logger.log("re-register sensor listener");
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        try {
            sm.unregisterListener(this);
        } catch (Exception e) {
            if (BuildConfig.DEBUG) Logger.log(e);
            e.printStackTrace();
        }

        if (BuildConfig.DEBUG) {
            Logger.log("step sensors: " + sm.getSensorList(Sensor.TYPE_STEP_COUNTER).size());
            if (sm.getSensorList(Sensor.TYPE_STEP_COUNTER).size() < 1) return; // emulator
            Logger.log("default: " + sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER).getName());
        }

        // enable batching with delay of max 5 min
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER),
                SensorManager.SENSOR_DELAY_NORMAL, (int) (5 * MICROSECONDS_IN_ONE_MINUTE));
    }
}
