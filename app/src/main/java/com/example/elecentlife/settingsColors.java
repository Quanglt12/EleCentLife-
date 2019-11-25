package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

public class settingsColors extends AppCompatActivity {
    GlobalVar GLOBALVARIABLES = new GlobalVar(getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_colors);

        BuildBoxes();
    }

    private void BuildBoxes() {
        String color = new String();


        //build spinners one by one
        Spinner WSColor = (Spinner) findViewById(R.id.WSColor);
        Spinner DLColor = (Spinner) findViewById(R.id.DLColor);
        Spinner CHColor = (Spinner) findViewById(R.id.CHColor);
        Spinner MTColor = (Spinner) findViewById(R.id.MTColor);
        Spinner OTColor = (Spinner) findViewById(R.id.OTColor);

        //set spinner values
        WSColor.setSelection(getColorIndex(GLOBALVARIABLES.getColor(0)));
        DLColor.setSelection(getColorIndex(GLOBALVARIABLES.getColor(1)));
        CHColor.setSelection(getColorIndex(GLOBALVARIABLES.getColor(2)));
        MTColor.setSelection(getColorIndex(GLOBALVARIABLES.getColor(3)));
        OTColor.setSelection(getColorIndex(GLOBALVARIABLES.getColor(4)));
    }

    private void saveColors() {
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

        GLOBALVARIABLES.setColor((colorArr));
    }

    private int getColorIndex(String color) {
        if (color.equalsIgnoreCase("Red"))
            return 1;
        else if (color.equalsIgnoreCase("Orange"))
            return 2;
        else if (color.equalsIgnoreCase("Yellow"))
            return 3;
        else if (color.equalsIgnoreCase("Green"))
            return 4;
        else if (color.equalsIgnoreCase("Blue"))
            return 5;
        else if (color.equalsIgnoreCase("Purple"))
            return 6;
        else if (color.equalsIgnoreCase("Pink"))
            return 7;
        else return 0;
    }
}
