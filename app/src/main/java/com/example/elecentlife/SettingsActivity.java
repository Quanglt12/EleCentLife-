package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {
    private TextView textViewNotif, textViewColors, textViewLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //set button go back to calendar
        Button calbtn = (Button) findViewById(R.id.calbutton2);
        calbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            }
        });

        //set button setting go to friend activity
        Button friendButton = (Button) findViewById(R.id.friendsbutton);
        friendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), FriendListActivity.class);
                startActivity(startIntent);
            }
        });

        //set button new event go to event activity
        Button eventButton = (Button) findViewById(R.id.newEventButton2);
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), NewEventActivity.class);
                startActivity(startIntent);
            }
        });

        textViewNotif = (TextView) findViewById(R.id.textViewNotif);
        textViewNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), settingsNotif.class);
                startActivity(startIntent);
            }
        });

        textViewColors = (TextView) findViewById(R.id.textViewColors);
        textViewColors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), settingsColors.class);
                startActivity(startIntent);
            }
        });

        textViewLogOut = (TextView) findViewById(R.id.textViewLogOut);
        textViewLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut(); //sign-out of the account
                Intent startIntent = new Intent(getApplicationContext(), Login.class);
                startActivity(startIntent);
            }
        });

    }
}
