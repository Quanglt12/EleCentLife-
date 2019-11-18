package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    GlobalVar changeSettings = new GlobalVar(this);
    TextView textViewNotif, textViewColors, textViewLogOut;
    String color;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

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
