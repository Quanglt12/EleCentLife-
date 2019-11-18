package com.example.elecentlife;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GlobalVar {
    private static ArrayList globalSettings; //index correlations: 0 - schedule reminder, 1 - quote notification
                                             //2 - breakfast recommendation, 3 - alarm time, 4 to 10 - colors
    private final String[] eventType = {"Work/School", "Deadline", "Casual Hangout", "Meeting", "Other"};

    public GlobalVar(Context context) {
        try {
            File file = new File(context.getExternalFilesDir(null), "globalsFile");

            //create the file if it doesn't exist
            if (!file.exists()) {
                file.createNewFile();
                globalSettings.set(0, "08:00 am");
                globalSettings.set(1, "08:05 am");
                globalSettings.set(2, "08:10 am");
                globalSettings.set(3, "07:30 am");

                //colors are a little different
                String[] colors = {"", "", "", "", "", "", ""};
                this.setColor(colors);
            }
            else {
                File globalsFile = new File(file, "globalsFile");
                FileInputStream fis = new FileInputStream(globalsFile);
                ObjectInputStream oin = new ObjectInputStream(fis);

                globalSettings = (ArrayList<String>) oin.readObject();
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
        globalSettings.set(0, schedRemind);
    }

    public String getSchedTime() {
        return globalSettings.get(0).toString();
    }

    public static void setQuoteTime(String quoteNotif) {
        globalSettings.set(1, quoteNotif);
    }

    public String getQuoteTime() {
        return globalSettings.get(1).toString();
    }

    public static void setBreakfastTime(String breakfastRecom) {
        globalSettings.set(2, breakfastRecom);
    }

    public static String getBreakfastTime() {
        return globalSettings.get(2).toString();
    }

    public static void setAlarmTime(String alarmTime) {
        globalSettings.set(3, alarmTime);
    }

    public static String getAlarmTime() {
        return globalSettings.get(0).toString();
    }

    public void setColor(String[] newColors) {
        globalSettings.set(4, newColors);
    }

    public String[] getColor() {
        String[] colors = {"","","","","","",""};
        for (int index = 4; index < 11; index++)
            colors[index-4] = globalSettings.get(index).toString();
        return colors;
    }

    public String[] getEventType() {
        return eventType;
    }
}
