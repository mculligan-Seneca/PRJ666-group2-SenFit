/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
OnlineClass class
This class repersents the data to be stored for an online class entity.
 */
package com.example.senfit.dataContext.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.sql.Timestamp;

@Entity(tableName="onlineClasses",foreignKeys = {@ForeignKey(entity = FitnessClass.class,
        parentColumns = "fitnessClassId",
        childColumns = "fitnessClassId"),
                @ForeignKey(entity=Trainer.class,
                        parentColumns = "trainerId",
                        childColumns="trainerId")})
public class OnlineClass {

    @PrimaryKey
    @SerializedName("id")
    private int onlineClassId;

    @ColumnInfo(name="class_date")
    private Date classDate;

    @ColumnInfo(name="start_time")
    private Timestamp startTime;

    @ColumnInfo(name="end_time")
    private Timestamp endTime;

    @ColumnInfo(name="fitnessClassId")
    private long fitnessClassId;//TODO:ADD foreign key or embedded

    @ColumnInfo(name="trainerId")
    private long trainerId;//TODO:ADD foreign key associations


    @ColumnInfo(name="enrolled")
    private boolean enrolled;

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

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

    public long getFitnessClassId() {
        return fitnessClassId;
    }

    public void setFitnessClassId(long fitnessClassId) {
        this.fitnessClassId = fitnessClassId;
    }

    public long getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(long trainerId) {
        this.trainerId = trainerId;
    }

    //TODO: add bridge tables
}
