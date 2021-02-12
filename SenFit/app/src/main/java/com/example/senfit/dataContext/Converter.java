/*
PRJ666 Sen-Fit
init date: Febuary 1st 2021
Author Mitchell Culligan
Version 1.0
 Converter class
This class is  to assist in converting complex data types from java into storable types for database
 */
package com.example.senfit.dataContext;

import androidx.room.TypeConverter;

import java.sql.Date;
import java.sql.Timestamp;

public class Converter {

    @TypeConverter
    public static Date fromLongToDate(Long value){
        return value==null?null:new Date(value);
    }

    @TypeConverter
    public static Long dateToLong(Date date){//converts date object to timestamp which can be stored in DB
        return date==null?null:date.getTime();
    }

    @TypeConverter
    public static Timestamp fromLongToTimestamp(Long value){
        return value==null?null:new Timestamp(value);
    }

    @TypeConverter
    public static Long timeStampToLong(Timestamp stamp){
        return stamp==null?null:stamp.getTime();
    }


}
