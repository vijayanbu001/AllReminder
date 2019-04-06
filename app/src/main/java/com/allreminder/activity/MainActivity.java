package com.allreminder.activity;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.allreminder.services.MyService;
import com.allreminder.adaptor.ToolAdaptor;
import com.allreminder.broadcastReceiver.BatteryStatusReceiver;
import com.allreminder.listener.CustomListener;
import com.allreminder.listener.GridItemClickListener;
import com.allreminder.utility.Utils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, CustomListener {
    private GridView gridView;
    BatteryStatusReceiver batteryStatusReceiver;
    private Intent batteryStatusIntent, serviceIntent;
    private ToolAdaptor toolAdaptor;
    Toolbar toolbar;
    String[] listArray;
    int[] listImgArray;
    Notification notification;
    MediaPlayer mp;
    Utils utils;
    String strRingtonePreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        gridView = (GridView) findViewById(R.id.tool_grid);

        listArray = getResources().getStringArray(R.array.list_content);

        listImgArray = getResources().getIntArray(R.array.list_img_content);
        utils = new Utils(this.getApplicationContext());

        batteryStatusReceiver = new BatteryStatusReceiver(this);
        serviceIntent = new Intent(this.getApplicationContext(), MyService.class);
        this.getApplicationContext().startService(serviceIntent);
        batteryStatusIntent = this.getApplicationContext().registerReceiver(batteryStatusReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        adaptorCaller();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }




    private void adaptorCaller() {
//        System.out.println("sound uri ===>"+ sound.getPath());

        toolAdaptor = new ToolAdaptor(this, batteryStatusIntent, R.layout.tool_grid_item, listArray, listImgArray);
        gridView.setAdapter(toolAdaptor);
        gridView.setOnItemClickListener(new GridItemClickListener(this.getApplicationContext()));
        toolAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
//        unregisterReceiver(batteryStatusReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp = utils.getRingtone();
    }

    @Override
    public void onBatteryStatusChange(Intent intent) {
        this.batteryStatusIntent = intent;

        adaptorCaller();
    }

    @Override
    public void showNotification(Intent intent) {

//        PendingIntent pendingIntent =

    }

    /*private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, batteryStatusIntent.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
*/
}
