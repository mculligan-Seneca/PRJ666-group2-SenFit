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
    private String classDate;

    @ColumnInfo(name="start_time")
    private String startTime;

    @ColumnInfo(name="end_time")
    private String endTime;

    @ColumnInfo(name="fitnessClassId")
    private int fitnessClassId;//TODO:ADD foreign key or embedded

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    @ColumnInfo(name="trainerId")
    private int trainerId;//TODO:ADD foreign key associations

    public int getOnlineClassId() {
        return onlineClassId;
    }

    public void setOnlineClassId(int onlineClassId) {
        this.onlineClassId = onlineClassId;
    }

    public String getClassDate() {
        return classDate;
    }

    public void setClassDate(String classDate) {
        this.classDate = classDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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
