package com.fearlessspider.god.widget;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class WidgetUpdateService extends JobIntentService {
    private static final int JOB_ID = 85;
    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        int steps = 0;
        final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, Widget.class));
        for(int appWidgetId : appWidgetIds) {
            appWidgetManager.updateAppWidget(appWidgetId, Widget.updateWidget(appWidgetId, this, steps));
        }
    }

    public static void enqueueUpdate(Context context) {
        enqueueWork(context, WidgetUpdateService.class, JOB_ID, new Intent());
    }
}
