package com.allreminder.services;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.BatteryManager;
import android.os.IBinder;

import com.allreminder.broadcastReceiver.BatteryStatusReceiver;
import com.allreminder.listener.CustomListener;
import com.allreminder.utility.Utils;

public class MyService extends Service implements CustomListener {
    BatteryStatusReceiver batteryStatusReceiver1;
    MediaPlayer mp;

    Intent batteryStatusIntent1;
    Utils utils;
    public MyService() {
        utils = new Utils(this);
    }

    @Override
    public IBinder onBind(Intent intent) {

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
//        super.onCreate();
        batteryStatusReceiver1 = new BatteryStatusReceiver(this);
        batteryStatusIntent1 = registerReceiver(batteryStatusReceiver1, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        mp = utils.getRingtone();
    }

    @Override
    public void onDestroy() {
//        super.onDestroy();
        unregisterReceiver(batteryStatusReceiver1);
    }


    @Override
    public void onBatteryStatusChange(Intent intent) {
        System.out.println("from  onBatteryStatusChange service....");

    }

    @Override
    public void showNotification(Intent intent) {
        int plugged = intent != null ? intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1) : -1;
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
        if (plugged > 0 && level > 80) {
            utils.play(mp);
        }
    }

}
