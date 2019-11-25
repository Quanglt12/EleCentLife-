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
            if (!file.exists()) {
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
            File file = Environment.getExternalStorageDirectory();
            File eventsFile = new File(file, "eventsFile");
            FileOutputStream fos = new FileOutputStream(eventsFile);
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(EventList);
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getEventList() {
        return EventList;
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
                break;
            }
        }
    }
}
