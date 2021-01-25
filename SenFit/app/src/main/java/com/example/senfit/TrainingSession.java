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
    private Date trainingDate;

    @ColumnInfo(name="start_time")
    private Timestamp startTime; //well have to see if date is really needed

    @ColumnInfo(name="end_time")
    private Timestamp endTime;

    @ColumnInfo(name="trainingPlanId")
    private int trainingPlanId;//TODO:add association or emmbedded


}
