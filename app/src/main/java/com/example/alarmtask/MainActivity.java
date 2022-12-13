package com.example.alarmtask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.alarmtask.databinding.ActivityMainBinding;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM));
binding.btnCancel.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        r.stop();
    }
});

binding.btnstart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (view.getId() == binding.btnstart.getId()) {
            startAlert(view);
        } else {
            if (alarmManager != null) {

                alarmManager.cancel(pendingIntent);
                Toast.makeText(MainActivity.this, "Alarm Disabled !!",Toast.LENGTH_LONG).show();

            }

        }
    }
});
    }
    public void startAlert(View view) {
        if(!binding.edttextforalarm.getText().toString().equals("")){
            int i = Integer.parseInt(binding.edttextforalarm.getText().toString());
            intent = new Intent(MainActivity.this,MyBroadcastReceiver.class);

            pendingIntent = PendingIntent.getBroadcast( MainActivity.this, 280192, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, Calendar.getInstance().getTimeInMillis() + (i * 1000), 1000 , pendingIntent);
            Toast.makeText(MainActivity.this, "Alarm will set in "+ i + " seconds", Toast.LENGTH_SHORT).show();

        }
        else {
            Toast.makeText(MainActivity.this, "Please Provide Time", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}