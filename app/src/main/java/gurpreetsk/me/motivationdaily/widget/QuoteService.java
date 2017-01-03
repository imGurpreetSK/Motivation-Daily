package gurpreetsk.me.motivationdaily.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.request.target.AppWidgetTarget;

import java.util.ArrayList;
import java.util.List;

import gurpreetsk.me.motivationdaily.R;
import gurpreetsk.me.motivationdaily.data.Database;
import gurpreetsk.me.motivationdaily.data.QuotesTable;
import gurpreetsk.me.motivationdaily.utils.Constants;

/**
 * Created by gurpreet on 03/01/17.
 */

public class QuoteService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }


    class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

        private AppWidgetTarget appWidgetTarget;
        private ArrayList<String> quotes = new ArrayList<>();
        private Context mContext;
        private int mAppWidgetId;


        public StackRemoteViewsFactory(Context context, Intent intent) {
            mContext = context;
            mAppWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }


        public void onCreate() {
            Cursor cursor = mContext.getContentResolver().query(QuotesTable.CONTENT_URI, null, null, null, null);
            List<Database> testRows = QuotesTable.getRows(cursor, true);
            for (Database element : testRows)
                quotes.add(element.quote);

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        public void onDestroy() {
            quotes.clear();
        }


        public int getCount() {
            return quotes.size();
        }


        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_stack);
            rv.setTextViewText(R.id.widget_text, quotes.get(position));

            Bundle extras = new Bundle();
            extras.putString(Constants.QUOTE_KEY, quotes.get(position));
            Intent fillInIntent = new Intent();
            fillInIntent.putExtras(extras);
            rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return rv;
        }


        public RemoteViews getLoadingView() {
            return null;
        }


        public int getViewTypeCount() {
            return 1;
        }


        public long getItemId(int position) {
            return position;
        }


        public boolean hasStableIds() {
            return true;
        }


        public void onDataSetChanged() {}

    }
}
