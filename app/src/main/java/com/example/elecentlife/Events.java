package com.example.elecentlife;

import java.util.ArrayList;
import java.util.List;

public class Events {
    private static List<String> EventList = new ArrayList<>();

    public List<String> getEventList() {
        return EventList;
    }

    public void addEvent(String newEvent) {
        EventList.add(newEvent);
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
