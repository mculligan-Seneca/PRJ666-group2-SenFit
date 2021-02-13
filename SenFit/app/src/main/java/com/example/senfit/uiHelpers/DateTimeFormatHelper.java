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

public class DateTimeFormatHelper {

    public static String formatDate(Date date){
        return date.toString();//TODO:implement this function
    }

    public static String formatTime(Timestamp timestamp){
        return timestamp.toString();
    }
}
