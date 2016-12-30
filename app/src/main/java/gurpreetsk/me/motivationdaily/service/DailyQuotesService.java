package gurpreetsk.me.motivationdaily.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import gurpreetsk.me.motivationdaily.receiver.Alarm;

/**
 * Created by Gurpreet on 30/12/16.
 */

public class DailyQuotesService extends Service {

    Alarm alarm = new Alarm();


    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        alarm.setAlarm(this);
        return START_STICKY;
    }


    @Override
    public void onStart(Intent intent, int startId) {
        alarm.setAlarm(this);
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}