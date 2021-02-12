/*
PRJ666 Sen-Fit
init date: Febuary 11th 2021
Author Mitchell Culligan
Version 1.0
DateHelper class
This class is a helper class for the covid survey in handling data operations

 */
package com.example.senfit.covidLog;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.time.DateTimeException;

public class DateHelper {

    private static final String ERR_TAG="date_helper_except";

    public static boolean compareToDate(Date date){//compares the date to today
            boolean status=false;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
           try {
               status = compareDateRecent(date);
           }catch(DateTimeException dte){
               Log.e(ERR_TAG,dte.getMessage());
               //if it throws exception it returns false
           }
        }
        else{
            status = compareDateOld(date);
        }
        return status;

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private static boolean compareDateRecent(Date date) throws DateTimeException{//requires api and throws exception
        Instant today = null;


        today = getCurrentDate().toInstant().truncatedTo(ChronoUnit.DAYS);
        Instant dateInstant = date.toInstant().truncatedTo(ChronoUnit.DAYS);
        return today.equals(dateInstant);

    }
    private static boolean compareDateOld(Date date){
        Calendar cal = Calendar.getInstance();
        Calendar calToday = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR)==calToday.get(Calendar.YEAR) && cal.get(Calendar.MONTH)
                == calToday.get(Calendar.MONTH) &&
                cal.get(Calendar.DAY_OF_MONTH)==calToday.get(Calendar.DAY_OF_MONTH);
    }

    public static Date getDateMinusWeeks(int weeks){//retrieves the current date minus a certain number of weeks
        Date date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            try {

                date = getDateMinusWeeksRecent(weeks);
            }catch(DateTimeException dte){
                Log.e(ERR_TAG,dte.getMessage());
                date = getDateMinusWeeksOld(weeks);
                //if throws exception perform old operation

            }
        }
        else{
            date = getDateMinusWeeksOld(weeks);
        }

        return date;
    }


    private static Date getDateMinusWeeksOld(int weeks){
      return new Date(System.currentTimeMillis()-(weeks*7*24*3600*1000));//subtracts milliseconds
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Date getDateMinusWeeksRecent(int weeks){
        LocalDate localDate = LocalDate.now().minusWeeks(weeks);//retrieves local date and subtracts weeks
      return  new Date(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant().getEpochSecond());
        //converts local date object to date object
    }
    public static Date getCurrentDate(){ //returns the current date

        return new Date(System.currentTimeMillis());
    }
}
