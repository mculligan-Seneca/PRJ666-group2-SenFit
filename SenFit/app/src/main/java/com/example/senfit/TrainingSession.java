/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
TrainingSession class
 */
package com.example.senfit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity(tableName = "trainingSessions")
public class TrainingSession {
    @PrimaryKey(autoGenerate = true)
    private int trainingSessionId;

    @ColumnInfo(name="training_date")
    private String trainingDate;

    @ColumnInfo(name="start_time")
    private String startTime; //well have to see if date is really needed

    @ColumnInfo(name="end_time")
    private String endTime;

    @ColumnInfo(name="trainingPlanId")
    private int trainingPlanId;//TODO:add association or emmbedded


    public int getTrainingSessionId() {
        return trainingSessionId;
    }

    public void setTrainingSessionId(int trainingSessionId) {
        this.trainingSessionId = trainingSessionId;
    }

    public String getTrainingDate() {
        return trainingDate;
    }

    public void setTrainingDate(String trainingDate) {
        this.trainingDate = trainingDate;
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

    public int getTrainingPlanId() {
        return trainingPlanId;
    }

    public void setTrainingPlanId(int trainingPlanId) {
        this.trainingPlanId = trainingPlanId;
    }
}
