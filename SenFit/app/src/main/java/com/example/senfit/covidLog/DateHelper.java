/*
PRJ666 Sen-Fit
init date: Febuary 11th 2021
Author Mitchell Culligan
Version 1.0
DateHelper class
This class is a helper class for the covid survey in handling data operations

 */
package com.example.senfit.covidLog;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class DateHelper {


    public static boolean compareToDate(Date date){//compares the date to today


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Instant today = null;


            today = getCurrentDate().toInstant().truncatedTo(ChronoUnit.DAYS);
            Instant dateInstant = date.toInstant().truncatedTo(ChronoUnit.DAYS);
            return today.equals(dateInstant);
        }
        else{
            Calendar cal = Calendar.getInstance();
            Calendar calToday = Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.YEAR)==calToday.get(Calendar.YEAR) && cal.get(Calendar.MONTH)
                    == calToday.get(Calendar.MONTH) &&
                    cal.get(Calendar.DAY_OF_MONTH)==calToday.get(Calendar.DAY_OF_MONTH);
        }

    }

    public static Date getDateMinusWeeks(int weeks){//retrieves the current date minus a certain number of weeks
        Date date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate localDate = LocalDate.now().minusWeeks(weeks);//substracts number of weeks
            date = new Date(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond());
        }
        else{
            date = new Date(System.currentTimeMillis()-(weeks*7*24*3600*1000));//subtracts milliseconds
        }

        return date;
    }


    public static Date getCurrentDate(){ //returns the current date

        return new Date(System.currentTimeMillis());
    }
}
