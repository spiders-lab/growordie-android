package com.fearlessspider.god.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.fearlessspider.god.MainActivity;
import com.fearlessspider.god.R;

public class Widget extends AppWidgetProvider {
    public static RemoteViews updateWidget(final int appWidgetId, final Context context, final int steps) {
        final PendingIntent pendingIntent = PendingIntent.getActivity(context, appWidgetId, new Intent(context, MainActivity.class), 0);
        final RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widget);

        view.setOnClickPendingIntent(R.id.widget, pendingIntent);
        view.setTextViewText(R.id.widgetsteps, String.valueOf(0));

        return view;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        WidgetUpdateService.enqueueUpdate(context);
    }
}
