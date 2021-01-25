/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
OnlineClass class
This class repersents the data to be stored for an online class entity.
 */
package com.example.senfit;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Timestamp;

@Entity(tableName="onlineClasses")
public class OnlineClass {

    @PrimaryKey(autoGenerate=true)
    private int onlineClassId;

    @ColumnInfo(name="class_date")
    private Date classDate;

    @ColumnInfo(name="start_time")
    private Timestamp startTime;

    @ColumnInfo(name="end_time")
    private Timestamp endTime;

    @ColumnInfo(name="trainerId")
    public int trainerId;//TODO:ADD foreign key associations

        //TODO: add bridge tables
}
