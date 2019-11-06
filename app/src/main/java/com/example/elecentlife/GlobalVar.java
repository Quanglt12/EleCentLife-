package com.example.elecentlife;

public class GlobalVar {
    private static String schedRemind = "08:00 am";
    private static String quoteNotif = "08:05 am";
    private static String breakfastRecom = "08:10 am";
    private static String alarmTime = "07:30 am";
    private static String[] color = {"#FF0000", "#FFFF00", "#008000", "#0000FF", "#800080"};
    private static String[] eventType = {"Work/School", "Deadline", "Casual Hangout", "Meeting", "Other"};

    public GlobalVar() {
        //This will eventually load the variables from the saved files
    }

    public void setSchedTime(String schedRemind) {
        GlobalVar.schedRemind = schedRemind;
    }

    public String getSchedTime() {
        return schedRemind;
    }

    public static void setQuoteTime(String quoteNotif) {
        GlobalVar.quoteNotif = quoteNotif;
    }

    public String getQuoteTime() {
        return quoteNotif;
    }

    public static void setBreakfastTime(String breakfastRecom) {
        GlobalVar.breakfastRecom = breakfastRecom;
    }

    public static String getBreakfastTime() {
        return breakfastRecom;
    }

    public static void setAlarmTime(String alarmTime) {
        GlobalVar.alarmTime = alarmTime;
    }

    public static String getAlarmTime() {
        return alarmTime;
    }

    public void setColor(int index, String newColor) {
        color[index] = newColor;
    }

    public String getColor(int index) {
        return color[index];
    }

    public String getEventType(int index) {
        return eventType[index];
    }
}
