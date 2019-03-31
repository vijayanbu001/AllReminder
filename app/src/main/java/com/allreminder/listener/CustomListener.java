package com.allreminder.listener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public interface CustomListener {

    void onBatteryStatusChange(Intent intent);
    void showNotification(Intent intent);
}
