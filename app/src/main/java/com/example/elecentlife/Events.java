package com.example.elecentlife;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.Vector;

public class Events {
    private static List<String[]> EventList;

    public List<String[]> getEventList() {
        return EventList;
    }

    public void addEvent(Intent newEvent) {

        String[] tempArr = new String[] {"","","","","","","",""};
        tempArr = newEvent.getStringArrayExtra("string-array");

        EventList.add(tempArr);
    }

    public void removeEvent(String[] event) {
        for (int index = 0; index < EventList.size(); index++ ) {
            String[] tempArr = EventList.get(index);
            if (tempArr == event) {
                EventList.remove(index);
                break;
            }
        }
    }
}
