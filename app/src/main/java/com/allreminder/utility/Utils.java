package com.allreminder.utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;

public class Utils {
    Context context;
    public Utils(Context context) {
        this.context  = context;

    }

    public MediaPlayer getRingtone(){
        SharedPreferences preference = PreferenceManager.getDefaultSharedPreferences(context);
        String strRingtonePreference = preference.getString("notifications_new_message_ringtone", "DEFAULT_SOUND");
        System.out.println("strRingtonePreference"+strRingtonePreference);
        System.out.println("Uri.parse(strRingtonePreference)"+ Uri.parse(strRingtonePreference));
        return MediaPlayer.create(context, Uri.parse(strRingtonePreference));
    }

    public void play(MediaPlayer mp) {
        if (mp != null)
            mp.start();
    }
}
