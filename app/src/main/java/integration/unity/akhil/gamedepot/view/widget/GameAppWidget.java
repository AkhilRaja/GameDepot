package integration.unity.akhil.gamedepot.view.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import integration.unity.akhil.gamedepot.R;
import integration.unity.akhil.gamedepot.view.LoginActivity;
import integration.unity.akhil.gamedepot.view.MainActivity;

/**
 * Implementation of App Widget functionality.
 */
public class GameAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.game_app_widget);
//
        // set intent for widget service that will create the views
        Intent serviceIntent = new Intent(context, StackWidgetService.class);
        serviceIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        serviceIntent.setData(Uri.parse(serviceIntent.toUri(Intent.URI_INTENT_SCHEME))); // embed extras so they don't get ignored

        views.setRemoteAdapter(R.id.stackWidgetView, serviceIntent);
        views.setEmptyView(R.id.stackWidgetView, R.id.stackWidgetEmptyView);

        // set intent for item click (opens main activity)
//        Intent viewIntent = new Intent(context, LoginActivity.class);
//        PendingIntent viewPendingIntent = PendingIntent.getActivity(context, 0, viewIntent, 0);
//        views.setOnClickPendingIntent(R.id.stackWidgetView,viewPendingIntent);

        // Instruct the widget manager to update the widget
//        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetId, R.id.stackWidgetView);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

