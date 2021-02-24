/*
 PRJ666 Sen-Fit
 init date: Feb 11th 2021
 Author Mitchell Culligan
 Version 1.0
Date Time format helper
This class helps convert date and time data types into formatted strings that are easy for the UI to display
 */
package com.example.senfit.uiHelpers;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DateTimeFormatHelper {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MMM dd, YYYY");
    public static String formatDate(Date date){
        return DATE_FORMAT.format(date);//TODO:implement this function
    }

    public static String formatTime(Timestamp timestamp){
        return timestamp.toString();
    }
}
