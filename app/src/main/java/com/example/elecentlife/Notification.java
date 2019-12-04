package com.example.elecentlife;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class Notification extends Application {

    public static final String NOTIF_1_ID = "ScheduleReminder";
    public static final String NOTIF_2_ID = "Quote";
    public static final String NOTIF_3_ID = "BreakfastRecom";
    public static final String NOTIF_4_ID = "GroupEventRequest";
    public static final String NOTIF_5_ID = "Alarm";
    @Override
    public void onCreate() {
        super.onCreate();

        createNotifications();

    }
    private void createNotifications() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel scheduleReminder = new NotificationChannel(
                    NOTIF_1_ID,
                    "Schedule Reminder",
                    NotificationManager.IMPORTANCE_HIGH
            );
            scheduleReminder.setDescription("This is a Schedule Reminder");

            NotificationChannel quote = new NotificationChannel(
                    NOTIF_2_ID,
                    "Quote",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            quote.setDescription("Quote of the day: ");

            NotificationChannel breakfastRecom = new NotificationChannel(
                    NOTIF_3_ID,
                    "Breakfast Recommendation",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            breakfastRecom.setDescription("Today's breakfast ideas: ");

            NotificationChannel groupEventRequest = new NotificationChannel(
                    NOTIF_4_ID,
                    "Group Event Request",
                    NotificationManager.IMPORTANCE_HIGH
            );
            groupEventRequest.setDescription("You are invited to an event");

            NotificationChannel alarm = new NotificationChannel(
                    NOTIF_5_ID,
                    "Alarm",
                    NotificationManager.IMPORTANCE_HIGH
            );
            alarm.setDescription("Alarm");

            NotificationManager manager = getSystemService(NotificationManager.class);
            assert manager != null;
            manager.createNotificationChannel(scheduleReminder);
            manager.createNotificationChannel(quote);
            manager.createNotificationChannel(breakfastRecom);
            manager.createNotificationChannel(groupEventRequest);
            manager.createNotificationChannel(alarm);
        }
    }
}