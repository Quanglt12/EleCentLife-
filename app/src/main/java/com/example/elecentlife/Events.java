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
import java.util.List;

public class Events {
    private static List<String> EventList = new ArrayList<>();

    //constructor used to load events from the file saved on the device
    public Events(Context context) {
        try {
            File path = Environment.getExternalStorageDirectory();
            File file = new File(path, "eventsFile");
            File eventsFile = new File(file, "eventsFile.txt");
            //create the file if it doesn't exist
            if (!eventsFile.exists()) {
                file.mkdirs();
                eventsFile.createNewFile();
            }
            else {
                FileInputStream fis = new FileInputStream(eventsFile);
                ObjectInputStream oin = new ObjectInputStream(fis);

                //set EventList to contents of the file
                EventList = (List<String>) oin.readObject();
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

    private void saveEvents () {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "eventsFile");
            File eventsFile = new File(file, "eventsFile.txt");
            FileOutputStream fos = new FileOutputStream(eventsFile);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(EventList);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //run in a loop to get all events
    public String getEventList(int index) {
        return EventList.get(index);
    }

    public void addEvent(String newEvent) {
        EventList.add(newEvent);
        this.saveEvents();
    }

    public void removeEvent(String event) {
        for (int index = 0; index < EventList.size(); index++ ) {
            String tempStr = EventList.get(index);

            if (tempStr == event) {
                EventList.remove(index);
                this.saveEvents();
                break;
            }
        }
    }
}
