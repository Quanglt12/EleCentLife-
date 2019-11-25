package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class settingsColors extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_colors);

        BuildBoxes();

        Button saveChanges = (Button) findViewById(R.id.saveButton);
        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveColors();
            }
        });
    }

    private void BuildBoxes() {
        GlobalVar globalVariables = new GlobalVar();
        //build spinners one by one
        Spinner WSColor = (Spinner) findViewById(R.id.WSColor);
        Spinner DLColor = (Spinner) findViewById(R.id.DLColor);
        Spinner CHColor = (Spinner) findViewById(R.id.CHColor);
        Spinner MTColor = (Spinner) findViewById(R.id.MTColor);
        Spinner OTColor = (Spinner) findViewById(R.id.OTColor);

        //set spinner values
        WSColor.setSelection(getColorIndex(globalVariables.getColor(0)));
        DLColor.setSelection(getColorIndex(globalVariables.getColor(1)));
        CHColor.setSelection(getColorIndex(globalVariables.getColor(2)));
        MTColor.setSelection(getColorIndex(globalVariables.getColor(3)));
        OTColor.setSelection(getColorIndex(globalVariables.getColor(4)));
    }

    private void saveColors() {
        GlobalVar globalVariables = new GlobalVar();
        Spinner WSColor = (Spinner) findViewById(R.id.WSColor);
        Spinner DLColor = (Spinner) findViewById(R.id.DLColor);
        Spinner CHColor = (Spinner) findViewById(R.id.CHColor);
        Spinner MTColor = (Spinner) findViewById(R.id.MTColor);
        Spinner OTColor = (Spinner) findViewById(R.id.OTColor);
        String[] colorArr = new String[] {"","","","",""};

        colorArr[0] = WSColor.getSelectedItem().toString();
        colorArr[1] = DLColor.getSelectedItem().toString();
        colorArr[2] = CHColor.getSelectedItem().toString();
        colorArr[3] = MTColor.getSelectedItem().toString();
        colorArr[4] = OTColor.getSelectedItem().toString();

        globalVariables.setColor((colorArr));
        globalVariables.saveGlobalVars();
    }

    private int getColorIndex(String color) {
        if (color.equalsIgnoreCase("Red"))
            return 0;
        else if (color.equalsIgnoreCase("Orange"))
            return 1;
        else if (color.equalsIgnoreCase("Yellow"))
            return 2;
        else if (color.equalsIgnoreCase("Green"))
            return 3;
        else if (color.equalsIgnoreCase("Blue"))
            return 4;
        else if (color.equalsIgnoreCase("Purple"))
            return 5;
        else if (color.equalsIgnoreCase("Pink"))
            return 6;
        else return -1;
    }
}
