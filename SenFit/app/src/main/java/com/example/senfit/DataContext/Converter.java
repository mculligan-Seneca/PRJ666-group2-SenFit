/*
PRJ666 Sen-Fit
init date: Febuary 1st 2021
Author Mitchell Culligan
Version 1.0
 Converter class
This class is  to assist in converting complex data types from java into storable types for database
 */
package com.example.senfit.DataContext;

import androidx.room.TypeConverter;

import java.sql.Date;

public class Converter {

    @TypeConverter
    public static Date fromTimeStamp(Long value){
        return value==null?null:new Date(value);
    }

    @TypeConverter
    public static Long dateToTimeStamp(Date date){//converts date object to timestamp which can be stored in DB
        return date==null?null:date.getTime();
    }
}
