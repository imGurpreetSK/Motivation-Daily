package gurpreetsk.me.motivationdaily.receiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;

/**
 * Created by Gurpreet on 30/12/16.
 */

public class Alarm extends BroadcastReceiver {

    private static final String TAG = "Alarm";


    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

//        String[] data = new String[2];
//        // Put here YOUR code.
//        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show(); // For example
//        try {
//            Document doc = Jsoup.connect("https://www.brainyquote.com/quotes_of_the_day.html").get();
//            data[0] = doc.select("span.bqQuoteLink a").first().html();
//            data[1] = doc.select("div.bq-aut a").first().html();
////            Log.i(TAG, "doInBackground: " + Arrays.toString(data));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Log.i(TAG, "onReceive: ALARM");

        wl.release();
    }


    public void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        //Repeat after 3 hours
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 60 * 3, pendingIntent);
    }


    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }


}
