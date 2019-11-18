package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class settingsNotif extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notif);

        BuildSchedBoxes();
        BuildMotivBoxes();
        BuildBfastBoxes();
        BuildAlarmBoxes();

        Button saveChanges = (Button) findViewById(R.id.saveButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveNewSettings();
            }
        });
    }

    private void BuildSchedBoxes() {
        GlobalVar globalVariables = new GlobalVar(this);
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
    }

    private void BuildMotivBoxes() {
        GlobalVar globalVariables = new GlobalVar(this);
        String motivTime = globalVariables.getQuoteTime();
        EditText motivTimeTB1 = (EditText) findViewById(R.id.motivTimeTB1);
        EditText motivTimeTB2 = (EditText) findViewById(R.id.motivTimeTB2);
        Spinner motivTimeAmPm = (Spinner) findViewById(R.id.motivTimeSP);

        String tempStr = motivTime.substring(0, 2);
        motivTimeTB1.setText(tempStr);

        tempStr = motivTime.substring(3, 5);
        motivTimeTB2.setText(tempStr);

        tempStr = motivTime.substring(6, 8);
        if (tempStr == "am")
            motivTimeAmPm.setSelection(0);
        else if (tempStr == "pm")
            motivTimeAmPm.setSelection(1);
    }

    private void BuildBfastBoxes() {
        GlobalVar globalVariables = new GlobalVar(this);
        String bfastTime = globalVariables.getBreakfastTime();
        EditText bfastTimeTB1 = (EditText) findViewById(R.id.bfastTimeTB1);
        EditText bfastTimeTB2 = (EditText) findViewById(R.id.bfastTimeTB2);
        Spinner bfastTimeAmPm = (Spinner) findViewById(R.id.bfastTimeSP);

        String tempStr = bfastTime.substring(0, 2);
        bfastTimeTB1.setText(tempStr);

        tempStr = bfastTime.substring(3, 5);
        bfastTimeTB2.setText(tempStr);

        tempStr = bfastTime.substring(6, 8);
        if (tempStr == "am")
            bfastTimeAmPm.setSelection(0);
        else if (tempStr == "pm")
            bfastTimeAmPm.setSelection(1);
    }

    private void BuildAlarmBoxes() {
        GlobalVar globalVariables = new GlobalVar(this);
        String alarmTime = globalVariables.getAlarmTime();
        EditText alarmTimeTB1 = (EditText) findViewById(R.id.alarmTimeTB1);
        EditText alarmTimeTB2 = (EditText) findViewById(R.id.alarmTimeTB2);
        Spinner alarmTimeAmPm = (Spinner) findViewById(R.id.alarmTimeSP);

        String tempStr = alarmTime.substring(0, 2);
        alarmTimeTB1.setText(tempStr);

        tempStr = alarmTime.substring(3, 5);
        alarmTimeTB2.setText(tempStr);

        tempStr = alarmTime.substring(6, 8);
        if (tempStr == "am")
            alarmTimeAmPm.setSelection(0);
        else if (tempStr == "pm")
            alarmTimeAmPm.setSelection(1);
    }

    private void SaveNewSettings() {
        GlobalVar globalVariables = new GlobalVar(this);

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
    }

    private String ConcatStrings(String h, String m, String AmPm) {
        String Concat = h + ":" + m + " " + AmPm;
        return Concat;
    }
}