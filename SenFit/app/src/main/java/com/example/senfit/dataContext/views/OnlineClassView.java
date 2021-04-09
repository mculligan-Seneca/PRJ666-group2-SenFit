/*
PRJ666 Sen-Fit
init date: April 9th 2021
Author Mitchell Culligan
Version 1.0
OnlineClassView
This view repersents how online class  data will be shown in the view
 */
package com.example.senfit.dataContext.views;

import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

import java.sql.Date;
import java.sql.Timestamp;

@DatabaseView(viewName = "onlineClassView",value="Select onlineClassId, trainerId, fitnessClassId,"+
        " first_name || ' ' || last_name as instructorName, class_date, start_time, end_time, enrolled  "+
        "from onlineClasses join trainers using(trainerId) "+
        "join fitnessClass using(fitnessClassId);")
public class OnlineClassView {

    public int onlineClassId;
    public int trainerId;
    public int fitnessClassId;
    @ColumnInfo(name="instructorName")
    public String instructorName;
    @ColumnInfo(name="fitness_class_name")
    public String className;
    @ColumnInfo(name="class_date")
    public Date classDate;
    @ColumnInfo(name="start_time")
    public Timestamp startTime;

    @ColumnInfo(name="end_time")
    public Timestamp endTime;

    public boolean enrolled;

    public OnlineClassView(int onlineClassId,int trainerId, int fitnessClassId, String instructorName,String className
    , Date classDate, Timestamp startTime, Timestamp endTime, boolean enrolled){
        this.onlineClassId=onlineClassId;
        this.trainerId=trainerId;
        this.fitnessClassId=fitnessClassId;
        this.instructorName=instructorName;
        this.className=className;
        this.classDate=classDate;
        this.startTime=startTime;
        this.endTime=endTime;
        this.enrolled=enrolled;
    }

}
