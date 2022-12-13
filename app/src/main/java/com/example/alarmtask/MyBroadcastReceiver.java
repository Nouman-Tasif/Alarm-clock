package com.example.alarmtask;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.Toast;

import com.example.alarmtask.databinding.ActivityMainBinding;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override

    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Time Up... Now Vibrating !!!",
                Toast.LENGTH_LONG).show();
        final Ringtone r = RingtoneManager.getRingtone(context,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
        r.play();

        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);

        vibrator.vibrate(9000);

    }

}
