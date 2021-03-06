package com.example.elecentlife;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {
    private final String TIMEFORMAT = "(0[1-9]|1[0-2]):(([0-5])([0-9]))";
    private final String DATEFORMAT = "(0[1-9]|1[0-2])/(0[1-9]|[1-2][0-9]|3[0-1])/[0-9][0-9][0-9][0-9]";

    public boolean isValidTime (String time) {
        Pattern pattern = Pattern.compile(TIMEFORMAT);
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }

    public boolean isValidDate (String date) {
        Pattern pattern = Pattern.compile(DATEFORMAT);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }
}
