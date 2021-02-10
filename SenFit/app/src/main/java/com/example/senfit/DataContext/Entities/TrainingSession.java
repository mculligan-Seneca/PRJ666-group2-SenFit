/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
TrainingSession class
 */
package com.example.senfit.DataContext.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName = "trainingSessions")
public class TrainingSession {
    @PrimaryKey(autoGenerate = true)
    private int trainingSessionId;

    @ColumnInfo(name="training_date")
    private Date trainingDate;

    @ColumnInfo(name="start_time")
    private Long startTime; //well have to see if date is really needed
    //Timestamp rpersented as long
    @ColumnInfo(name="end_time")
    private Long endTime;//Timestamp repersented as long

    @ColumnInfo(name="trainingPlanId")
    private int trainingPlanId;//TODO:add association or emmbedded


    public int getTrainingSessionId() {
        return trainingSessionId;
    }

    public void setTrainingSessionId(int trainingSessionId) {
        this.trainingSessionId = trainingSessionId;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public int getTrainingPlanId() {
        return trainingPlanId;
    }

    public void setTrainingPlanId(int trainingPlanId) {
        this.trainingPlanId = trainingPlanId;
    }
}
