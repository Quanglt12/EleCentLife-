package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class NewEventActivity extends AppCompatActivity {
    public ValidationHelper inputValidation = new ValidationHelper();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        Button saveChanges = (Button) findViewById(R.id.saveButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check date and times for valid input
                if (checkDateTime()) {
                    String tempStr = getSingleString();

                    //call addEvent function to save new event
                    Events EventClass = new Events(getApplicationContext());
                    EventClass.addEvent(tempStr);

                    Toast toast = Toast.makeText(getApplicationContext(),"New event saved.",Toast.LENGTH_SHORT);
                    toast.show();

                    Intent intent = new Intent(NewEventActivity.this, MainActivity.class);
                    startActivity(intent);
                }

                else {
                    if (checkSDate()) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Event date incorrect (MM/DD/YYYY)",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (checkSTime()) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Event start time incorrect (HH:MM)",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    if (checkETime()) {
                        Toast toast = Toast.makeText(getApplicationContext(),"Event end time incorrect (HH:MM)",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });
    }

    private boolean checkDateTime() {
        EditText EVENTSDATE = (EditText) findViewById(R.id.eventSDateET);
        EditText EVENTSTIME = (EditText) findViewById(R.id.eventSTimeET);
        EditText EVENTETIME = (EditText) findViewById(R.id.eventETimeET);

        if (inputValidation.isValidDate(EVENTSDATE.getText().toString()) && inputValidation.isValidTime(EVENTSTIME.getText().toString())
                && inputValidation.isValidTime(EVENTETIME.getText().toString())) {
            return true;
        }
        else
            return false;
    }

    private boolean checkSDate() {
        EditText EVENTSDATE = (EditText) findViewById(R.id.eventSDateET);

        if (!inputValidation.isValidDate(EVENTSDATE.getText().toString()))
            return true;
        else
            return false;
    }

    private boolean checkSTime() {
        EditText EVENTSTIME = (EditText) findViewById(R.id.eventSTimeET);

        if (!inputValidation.isValidTime(EVENTSTIME.getText().toString()))
            return true;
        else
            return false;
    }

    private boolean checkETime() {
        EditText EVENTETIME = (EditText) findViewById(R.id.eventETimeET);

        if (!inputValidation.isValidTime(EVENTETIME.getText().toString()))
            return true;
        else
            return false;
    }

    private String getSingleString() {
        Spinner EVENTTYPE = (Spinner) findViewById(R.id.eventTypeSP);
        Spinner EVENTCOLOR = (Spinner) findViewById(R.id.eventColorSP);
        Spinner STIMEAMPM = (Spinner) findViewById(R.id.eventSTimeAmPm);
        Spinner ETIMEAMPM = (Spinner) findViewById(R.id.eventETimeAmPm);
        EditText EVENTNAME = (EditText) findViewById(R.id.eventNameET);
        EditText EVENTNOTE = (EditText) findViewById(R.id.eventNoteET);
        EditText EVENTSDATE = (EditText) findViewById(R.id.eventSDateET);
        EditText EVENTSTIME = (EditText) findViewById(R.id.eventSTimeET);
        EditText EVENTETIME = (EditText) findViewById(R.id.eventETimeET);
        EditText EVENTRHOUR = (EditText) findViewById(R.id.remindHourET);
        EditText EVENTRMIN = (EditText) findViewById(R.id.remindMinET);

        String newEvent;

        //events are stored as <eventtype>|<eventcolor>|<eventname>|<eventnote>|<eventsdate>|<eventstime>|<eventetime>|<eventrtime>
        newEvent = EVENTTYPE.getSelectedItem().toString() + "|";
        newEvent = newEvent + EVENTCOLOR.getSelectedItem().toString() + "|";
        newEvent = newEvent + EVENTNAME.getText().toString() + "|";
        newEvent = newEvent + EVENTNOTE.getText().toString() + "|";
        newEvent = newEvent + EVENTSDATE.getText().toString() + "|";
        newEvent = newEvent + EVENTSTIME.getText().toString() + " " + STIMEAMPM.getSelectedItem().toString() + "|";
        newEvent = newEvent + EVENTETIME.getText().toString() + " " + ETIMEAMPM.getSelectedItem().toString() + "|";
        newEvent = newEvent + EVENTRHOUR.getText().toString() + " " + EVENTRMIN.getText().toString();

        return newEvent;
    }
}