package com.example.elecentlife;
import androidx.appcompat.app.AppCompatActivity;

import android.media.RingtoneManager;
import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TimePicker;
import android.media.Ringtone;

import java.util.Timer;
import java.util.TimerTask;

public class newEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        TimePicker remindTime;
        TextClock curTime;

        remindTime = findViewById(R.id.timePicker);
        curTime = findViewById(R.id.textClock);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE));

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask) {
            @Override
            public void run() {
                if(curTime.getText().toString().equals(TimeReminder())) {
                    r.play();
                else
                    r.stop();
                }
            }
        }

        public String TimeReminder() {
            int hourReminder = remindTime.getHour();
            int minsReminder = remindTime.getMinute();

            String stringTimeReminder = null;

            if(hourReminder>12) {
                hourReminder = hourReminder -12;
                stringTimeReminder = hourReminder.toString().concat(":").concat(minsReminder.toString()).concat(" PM");
            }
            else {
                stringTimeReminder = minsReminder.toString().concat(":").concat(minsReminder.toString()).concat(" AM");
            }
            return stringTimeReminder;
        }

    }

}