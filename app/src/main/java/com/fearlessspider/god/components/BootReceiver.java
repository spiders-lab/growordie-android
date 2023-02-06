package com.fearlessspider.god.components;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;

import com.fearlessspider.god.BuildConfig;
import com.fearlessspider.god.utils.API26Wrapper;
import com.fearlessspider.god.utils.Logger;

public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        if (BuildConfig.DEBUG) Logger.log("booted");

        SharedPreferences prefs = context.getSharedPreferences("G.O.D.", Context.MODE_PRIVATE);

        prefs.edit().remove("correctShutdown").apply();

        if (Build.VERSION.SDK_INT >= 26) {
            API26Wrapper.startForegroundService(context, new Intent(context, SensorListener.class));
        } else {
            context.startService(new Intent(context, SensorListener.class));
        }
    }
}
