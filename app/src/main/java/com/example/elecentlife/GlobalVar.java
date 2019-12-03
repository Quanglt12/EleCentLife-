package com.example.elecentlife;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GlobalVar {
    private static String[] globalSettings = new String[] {"","","","","","","","",""}; //index correlations: 0 - schedule reminder, 1 - quote notification
                                             //2 - breakfast recommendation, 3 - alarm time, 4 to 8 - colors
    private final String[] eventType = {"Work/School", "Deadline", "Casual Hangout", "Meeting", "Other"};

    public GlobalVar() {
        try {
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, "globalsFile");
            File globalsFile = new File(file, "globalsFile.txt");
            //globalsFile.delete();
            //create the file if it doesn't exist
            if (!globalsFile.exists()) {
                file.mkdirs();
                globalsFile.createNewFile();
                globalSettings[0] = "08:00 am";
                globalSettings[1] = "08:05 am";
                globalSettings[2] = "08:10 am";
                globalSettings[3] = "07:30 am";

                //colors are a little different
                String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue"};
                for (int index = 0; index < 5; index++) {
                    globalSettings[index+4] = colors[index];
                }
                this.saveGlobalVars();
            }
            else {
                FileInputStream fis = new FileInputStream(globalsFile);
                ObjectInputStream oin = new ObjectInputStream(fis);

                globalSettings = (String[]) oin.readObject();
                oin.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
    }

    public void saveGlobalVars() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "globalsFile");
            File globalsFile = new File (file, "globalsFile.txt");
            FileOutputStream fos = new FileOutputStream(globalsFile);
            ObjectOutputStream out = new ObjectOutputStream(fos);

            out.writeObject(globalSettings);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSchedTime(String schedRemind) {
        globalSettings[0] = schedRemind;
    }

    public String getSchedTime() {
        return globalSettings[0];
    }

    public static void setQuoteTime(String quoteNotif) {
        globalSettings[1] = quoteNotif;
    }

    public String getQuoteTime() {
        return globalSettings[1];
    }

    public static void setBreakfastTime(String breakfastRecom) {
        globalSettings[2] = breakfastRecom;
    }

    public static String getBreakfastTime() {
        return globalSettings[2];
    }

    public static void setAlarmTime(String alarmTime) {
        globalSettings[3] = alarmTime;
    }

    public static String getAlarmTime() {
        return globalSettings[3];
    }

    public void setColor(String[] newColors) {
        for (int index = 0; index < 5; index++) {
            globalSettings[index+4] = newColors[index];
        }
    }

    public String getColor(int index) {
            return globalSettings[index+4];
    }

    public String[] getEventType() {
        return eventType;
    }
}
