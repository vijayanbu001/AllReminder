package com.allreminder.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.os.Build;
import android.widget.GridView;
import android.widget.Toast;

import com.allreminder.activity.R;
import com.allreminder.adaptor.ToolAdaptor;
import com.allreminder.listener.CustomListener;

/**
 * Created by vijay on 15-08-2017.
 */

public class BatteryStatusReceiver extends BroadcastReceiver {

    private CustomListener customListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        int  health= intent.getIntExtra(BatteryManager.EXTRA_HEALTH,0);
        int  icon_small= intent.getIntExtra(BatteryManager.EXTRA_ICON_SMALL,0);
        int  level= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        int  plugged= intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,0);
        boolean  present= intent.getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
        int  scale= intent.getIntExtra(BatteryManager.EXTRA_SCALE,0);
        int  status= intent.getIntExtra(BatteryManager.EXTRA_STATUS,0);
        String  technology= intent.getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY);
        int  temperature= intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);
        int  voltage= intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE,0);
        customListener.onBatteryStatusChange(intent);
        BatteryManager bm = (BatteryManager) context.getSystemService(context.BATTERY_SERVICE);
        //        if (level > 80)
        customListener.showNotification(intent);
    }
    public BatteryStatusReceiver(CustomListener customListener){
        this.customListener = customListener;
    }
}
