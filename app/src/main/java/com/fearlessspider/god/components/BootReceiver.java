package com.fearlessspider.god.components;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import com.fearlessspider.god.BuildConfig;
import com.fearlessspider.god.db.Step;
import com.fearlessspider.god.repository.StepRepository;
import com.fearlessspider.god.utils.API26Wrapper;
import com.fearlessspider.god.utils.Logger;

public class BootReceiver extends BroadcastReceiver {

    private StepRepository stepRepository;

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (BuildConfig.DEBUG) Logger.log("booted");

        SharedPreferences prefs = context.getSharedPreferences("G.O.D.", Context.MODE_PRIVATE);

        stepRepository = new StepRepository((Application) context.getApplicationContext());

        if (!prefs.getBoolean("correctShutdown", false)) {
            if (BuildConfig.DEBUG) Logger.log("Incorrect shutdown");
            // can we at least recover some steps?
            int steps = Math.max(0, stepRepository.getCurrentStep().getValue().getSteps());
            if (BuildConfig.DEBUG) Logger.log("Trying to recover " + steps + " steps");
            stepRepository.update(new Step(0));
        }
        stepRepository.saveCurrentSteps(0);

        prefs.edit().remove("correctShutdown").apply();

        if (Build.VERSION.SDK_INT >= 26) {
            API26Wrapper.startForegroundService(context, new Intent(context, SensorListener.class));
        } else {
            context.startService(new Intent(context, SensorListener.class));
        }
    }
}
