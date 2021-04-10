/*
PRJ666 Sen-Fit
init date: April 9th 2021
Author Mitchell Culligan
Version 1.0
InPersonView
This view repersents how gym class data will be shown in view
 */
package com.example.senfit.dataContext.views;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.sql.Timestamp;

@DatabaseView(viewName = "InPersonView",value = "select gymClassId, trainerId, fitnessClassId,  "+
            "class_date, start_time, end_time, first_name || ' ' || last_name as instructorName, fitness_class_name, "+
            " enrolled from gymClass join trainers using(trainerId) join fitnessClass using(fitnessClassId);")
public class InPersonView {
    @ColumnInfo(name="gymClassId")
    public int gymClassId;
    @ColumnInfo(name="trainerId")
    public int trainerId;//TODO: ADD foreign key or embeeded

    @ColumnInfo(name="fitnessClassId")
    public int fitnessClassId;//TODO:ADD foreign key or




    @ColumnInfo(name="class_date")

    public Date classDate;

    @ColumnInfo(name="start_time")

    public Timestamp startTime;

    @ColumnInfo(name="end_time")

    public Timestamp endTime;



    @ColumnInfo(name="instructorName")
    public String instructorName;

    @ColumnInfo(name="fitness_class_name")
    public String className;



    @ColumnInfo(name="enrolled")
    public boolean enrolled;


}
