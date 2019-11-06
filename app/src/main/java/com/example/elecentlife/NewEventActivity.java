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
                String[] newEvent = new String[] {"","","","","","","",""};
                //Intent intent = new Intent(NewEventActivity.this, Events.class);

                newEvent[0] = EventType.getSelectedItem().toString();
                newEvent[1] = EventColor.getSelectedItem().toString();
                newEvent[2] = EventName.getText().toString();
                newEvent[3] = EventNote.getText().toString();
                newEvent[4] = EventSDate.getText().toString();
                newEvent[5] = EventSTime.getText().toString() + " " + STimeAmPm.getSelectedItem().toString();
                newEvent[6] = EventETime.getText().toString() + " " + ETimeAmPm.getSelectedItem().toString();
                newEvent[7] = EventRHour.getText().toString() + " " + EventRMin.getText().toString();

                //can't figure out how to get this shit to save
            }
        });
    }
}
