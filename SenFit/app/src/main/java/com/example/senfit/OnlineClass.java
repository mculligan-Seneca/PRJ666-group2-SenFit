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

    @ColumnInfo(name="fitnessClassId")
    private int fitnessClassId;//TODO:ADD foreign key or embedded

    @ColumnInfo(name="trainerId")
    private int trainerId;//TODO:ADD foreign key associations

    public int getOnlineClassId() {
        return onlineClassId;
    }

    public void setOnlineClassId(int onlineClassId) {
        this.onlineClassId = onlineClassId;
    }

    public Date getClassDate() {
        return classDate;
    }

    public void setClassDate(Date classDate) {
        this.classDate = classDate;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public int getFitnessClassId() {
        return fitnessClassId;
    }

    public void setFitnessClassId(int fitnessClassId) {
        this.fitnessClassId = fitnessClassId;
    }

    //TODO: add bridge tables
}