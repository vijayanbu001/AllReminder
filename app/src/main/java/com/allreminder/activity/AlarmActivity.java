package com.allreminder.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.allreminder.listener.CustomListener;


public class AlarmActivity extends AppCompatActivity implements CustomListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

    }

    @Override
    public void onBatteryStatusChange(Intent intent) {

    }

    @Override
    public void showNotification(Intent intent) {

    }
}
