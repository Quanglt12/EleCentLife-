package com.example.elecentlife;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class settingsNotif extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notif);

        BuildBoxes();

        //Create clocks

        Button sched_ButtonTimePicker = findViewById(R.id.Sched_TimePicker);
        sched_ButtonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "clock");
            }
        });

        Button quote_ButtonTimePicker = findViewById(R.id.Quote_TimePicker);
        quote_ButtonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "clock");
            }
        });

        Button bfast_ButtonTimePicker = findViewById(R.id.Bfast_TimePicker);
        bfast_ButtonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "clock");
            }
        });

        Button alarm_ButtonTimePicker = findViewById(R.id.Alarm_TimePicker);
        alarm_ButtonTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "clock");
            }
        });



        Button saveChanges = (Button) findViewById(R.id.saveButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveNewSettings();
                Toast toast = Toast.makeText(getApplicationContext(),"Notification times saved.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    private void BuildBoxes() {
        GlobalVar globalVariables = new GlobalVar();

        //build schedule boxes
        String schedTime = globalVariables.getSchedTime();
        EditText schedTimeTB1 = (EditText) findViewById(R.id.schedTimeTB1);
        EditText schedTimeTB2 = (EditText) findViewById(R.id.schedTimeTB2);
        Spinner schedTimeAmPm = (Spinner) findViewById(R.id.schedTimeSP);

        String tempStr = schedTime.substring(0, 2);
        schedTimeTB1.setText(tempStr);

        tempStr = schedTime.substring(3, 5);
        schedTimeTB2.setText(tempStr);

        tempStr = schedTime.substring(6, 8);
        if (tempStr == "am")
            schedTimeAmPm.setSelection(0);
        else if (tempStr == "pm")
            schedTimeAmPm.setSelection(1);

        //build quote boxes
        String motivTime = globalVariables.getQuoteTime();
        EditText motivTimeTB1 = (EditText) findViewById(R.id.motivTimeTB1);
        EditText motivTimeTB2 = (EditText) findViewById(R.id.motivTimeTB2);
        Spinner motivTimeAmPm = (Spinner) findViewById(R.id.motivTimeSP);

        tempStr = motivTime.substring(0, 2);
        motivTimeTB1.setText(tempStr);

        tempStr = motivTime.substring(3, 5);
        motivTimeTB2.setText(tempStr);

        tempStr = motivTime.substring(6, 8);
        if (tempStr == "am")
            motivTimeAmPm.setSelection(0);
        else if (tempStr == "pm")
            motivTimeAmPm.setSelection(1);

        //build breakfast boxes
        String bfastTime = globalVariables.getBreakfastTime();
        EditText bfastTimeTB1 = (EditText) findViewById(R.id.bfastTimeTB1);
        EditText bfastTimeTB2 = (EditText) findViewById(R.id.bfastTimeTB2);
        Spinner bfastTimeAmPm = (Spinner) findViewById(R.id.bfastTimeSP);

        tempStr = bfastTime.substring(0, 2);
        bfastTimeTB1.setText(tempStr);

        tempStr = bfastTime.substring(3, 5);
        bfastTimeTB2.setText(tempStr);

        tempStr = bfastTime.substring(6, 8);
        if (tempStr == "am")
            bfastTimeAmPm.setSelection(0);
        else if (tempStr == "pm")
            bfastTimeAmPm.setSelection(1);

        //build alarm boxes
        String alarmTime = globalVariables.getAlarmTime();
        EditText alarmTimeTB1 = (EditText) findViewById(R.id.alarmTimeTB1);
        EditText alarmTimeTB2 = (EditText) findViewById(R.id.alarmTimeTB2);
        Spinner alarmTimeAmPm = (Spinner) findViewById(R.id.alarmTimeSP);

        tempStr = alarmTime.substring(0, 2);
        alarmTimeTB1.setText(tempStr);

        tempStr = alarmTime.substring(3, 5);
        alarmTimeTB2.setText(tempStr);

        tempStr = alarmTime.substring(6, 8);
        if (tempStr == "am")
            alarmTimeAmPm.setSelection(0);
        else if (tempStr == "pm")
            alarmTimeAmPm.setSelection(1);
    }

    //set to 24hour standard

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        startAlarm(c);
    }
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        assert alarmManager != null;
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }





    private void SaveNewSettings() {
        GlobalVar globalVariables = new GlobalVar();

        EditText TB1 = (EditText) findViewById(R.id.schedTimeTB1);
        EditText TB2 = (EditText) findViewById(R.id.schedTimeTB2);
        Spinner AmPm = (Spinner) findViewById(R.id.schedTimeSP);
        globalVariables.setSchedTime(ConcatStrings(TB1.getText().toString(), TB2.getText().toString(), AmPm.getSelectedItem().toString()));

        TB1 = (EditText) findViewById(R.id.motivTimeTB1);
        TB2 = (EditText) findViewById(R.id.motivTimeTB2);
        AmPm = (Spinner) findViewById(R.id.motivTimeSP);
        globalVariables.setQuoteTime(ConcatStrings(TB1.getText().toString(), TB2.getText().toString(), AmPm.getSelectedItem().toString()));

        TB1 = (EditText) findViewById(R.id.bfastTimeTB1);
        TB2 = (EditText) findViewById(R.id.bfastTimeTB2);
        AmPm = (Spinner) findViewById(R.id.bfastTimeSP);
        globalVariables.setBreakfastTime(ConcatStrings(TB1.getText().toString(), TB2.getText().toString(), AmPm.getSelectedItem().toString()));

        TB1 = (EditText) findViewById(R.id.alarmTimeTB1);
        TB2 = (EditText) findViewById(R.id.alarmTimeTB2);
        AmPm = (Spinner) findViewById(R.id.alarmTimeSP);
        globalVariables.setAlarmTime(ConcatStrings(TB1.getText().toString(), TB2.getText().toString(), AmPm.getSelectedItem().toString()));

        globalVariables.saveGlobalVars();

        //globalVariables.delete();
    }

    private String ConcatStrings(String h, String m, String AmPm) {
        String Concat = h + ":" + m + " " + AmPm;
        return Concat;
    }
}