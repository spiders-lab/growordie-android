package com.fearlessspider.god.components;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.fearlessspider.god.BuildConfig;
import com.fearlessspider.god.repository.StepRepository;
import com.fearlessspider.god.utils.Logger;

public class ShutdownReceiver extends BroadcastReceiver {

    private StepRepository stepRepository;

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

        stepRepository = new StepRepository((Application) context.getApplicationContext());

        // TODO: Create new DB entry or add to exist one
    }

}
