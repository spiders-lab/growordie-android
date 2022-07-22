package com.fearlessspider.god.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fearlessspider.god.BuildConfig;
import com.fearlessspider.god.utils.DateUtil;
import com.fearlessspider.god.utils.Logger;

public class ShutdownReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (BuildConfig.DEBUG) Logger.log("Shutting down");

        context.startService(new Intent(context, SensorListener.class));

        // if the user used a root script for shutdown, the DEVICE_SHUTDOWN
        // broadcast might not be send. Therefore, the app will check this
        // setting on the next boot and displays an error message if it's not
        // set to true
        context.getSharedPreferences("G.O.D.", Context.MODE_PRIVATE).edit()
                .putBoolean("correctShutdown", true).commit();

        Database db = Database.getInstance(context);
        // if it's already a new day, add the temp. steps to the last one
        if (db.getSteps(DateUtil.getToday()) == Integer.MIN_VALUE) {
            int steps = db.getCurrentSteps();
            db.insertNewDay(Util.getToday(), steps);
        } else {
            db.addToLastEntry(db.getCurrentSteps());
        }
        // current steps will be reset on boot @see BootReceiver
        db.close();
    }

}
