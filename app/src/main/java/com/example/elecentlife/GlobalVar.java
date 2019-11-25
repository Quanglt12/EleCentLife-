package com.example.elecentlife;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class GlobalVar {
    private static String[] globalSettings = new String[] {"","","","","","","","",""}; //index correlations: 0 - schedule reminder, 1 - quote notification
                                             //2 - breakfast recommendation, 3 - alarm time, 4 to 8 - colors
    private final String[] eventType = {"Work/School", "Deadline", "Casual Hangout", "Meeting", "Other"};

    public GlobalVar(Context context) {
        try {
            File file = new File(context.getExternalFilesDir("global_settings"), "globalsFile");

            //create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
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
                File globalsFile = new File(file, "globalsFile");
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

    public void delete(Context context) {
        File file = new File(context.getExternalFilesDir("global_settings"), "globalsFile");
        file.delete();
    }

    public void saveGlobalVars() {
        try {
            File file = Environment.getExternalStorageDirectory();
            File eventsFile = new File(file, "eventsFile");
            FileOutputStream fos = new FileOutputStream(eventsFile);
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
