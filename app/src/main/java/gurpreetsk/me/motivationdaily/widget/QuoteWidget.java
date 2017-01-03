package gurpreetsk.me.motivationdaily.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.activities.FavoritesActivity;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * Created by gurpreet on 03/01/17.
 */

public class QuoteWidget extends AppWidgetProvider {

    public static final String TOAST_ACTION = "toast";
    public static final String DATABASE_CHANGED = "data changed";

    String quote;


    @Override
    public void onReceive(Context context, Intent intent) {
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        if (intent.getAction().equals(TOAST_ACTION)) {
            int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
            quote = intent.getStringExtra(Constants.QUOTE_KEY);

            Intent launchActivty = new Intent(context, FavoritesActivity.class);
            launchActivty.putExtra(Constants.QUOTE_KEY, quote);
            launchActivty.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(launchActivty);
        }
        if (intent.getAction().equals(DATABASE_CHANGED)) {
            final AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            onUpdate(context,
                    appWidgetManager,
                    manager.getAppWidgetIds(new ComponentName(context, QuoteWidget.class)));
        } else
            super.onReceive(context, intent);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {

            Intent intent = new Intent(context, QuoteService.class);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.news_widget);
            rv.setRemoteAdapter(appWidgetId, R.id.stack_view, intent);

            rv.setEmptyView(R.id.stack_view, R.id.empty_view);

            Intent toastIntent = new Intent(context, QuoteWidget.class);
            toastIntent.setAction(QuoteWidget.TOAST_ACTION);
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));
            PendingIntent toastPendingIntent = PendingIntent.getActivity(context, 0, toastIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);
            rv.setPendingIntentTemplate(R.id.stack_view, toastPendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, rv);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }


    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }


    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }


    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

}
