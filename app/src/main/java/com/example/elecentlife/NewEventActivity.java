package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class NewEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        //create fields as to not cause lag when clicking save event
        final Spinner EventType = (Spinner) findViewById(R.id.eventTypeSP);
        final Spinner EventColor = (Spinner) findViewById(R.id.eventColorSP);
        final Spinner STimeAmPm = (Spinner) findViewById(R.id.eventSTimeAmPm);
        final Spinner ETimeAmPm = (Spinner) findViewById(R.id.eventETimeAmPm);
        final EditText EventName = (EditText) findViewById(R.id.eventNameET);
        final EditText EventNote = (EditText) findViewById(R.id.eventNoteET);
        final EditText EventSDate = (EditText) findViewById(R.id.eventSDateET);
        final EditText EventSTime = (EditText) findViewById(R.id.eventSTimeET);
        final EditText EventETime = (EditText) findViewById(R.id.eventETimeET);
        final EditText EventRHour = (EditText) findViewById(R.id.remindHourET);
        final EditText EventRMin = (EditText) findViewById(R.id.remindMinET);

        Button saveChanges = (Button) findViewById(R.id.saveButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Events EventClass = new Events();
                String newEvent;

                //events are stored as <eventtype>|<eventcolor>|<eventname>|<eventnote>|<eventsdate>|<eventstime>|<eventetime>|<eventrtime>
                newEvent = EventType.getSelectedItem().toString() + "|";
                newEvent = newEvent + EventColor.getSelectedItem().toString() + "|";
                newEvent = newEvent + EventName.getText().toString() + "|";
                newEvent = newEvent + EventNote.getText().toString() + "|";
                newEvent = newEvent + EventSDate.getText().toString() + "|";
                newEvent = newEvent + EventSTime.getText().toString() + " " + STimeAmPm.getSelectedItem().toString() + "|";
                newEvent = newEvent + EventETime.getText().toString() + " " + ETimeAmPm.getSelectedItem().toString() + "|";
                newEvent = newEvent + EventRHour.getText().toString() + " " + EventRMin.getText().toString();

                //doesn't currently save for some reason
                EventClass.addEvent(newEvent);
            }
        });
    }
}
