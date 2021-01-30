/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
GymClass Class

 */
package com.example.senfit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;
import java.sql.Timestamp;

@Entity(tableName = "gymClass")
public class GymClass {
    @PrimaryKey(autoGenerate = true)
    private int gymClassId;//TODO:add constructor and getter/setter

    @ColumnInfo(name="class_date")
    private String classDate;

    @ColumnInfo(name="start_time")
    private String startTime;

    @ColumnInfo(name="end_time")
    private String endTime;

    @ColumnInfo(name="trainerId")
    private int trainerId;//TODO: ADD foreign key or embeeded

    @ColumnInfo(name="fitnessClassId")
    private int fitnessClassId;//TODO:ADD foreign key or embedded
    @ColumnInfo(name="gymLocationId")
    private int gymLocationId;//TODO: ADD foreign key or embedded

    public int getGymClassId() {
        return gymClassId;
    }

    public void setGymClassId(int gymClassId) {
        this.gymClassId = gymClassId;
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

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public int getFitnessClassId() {
        return fitnessClassId;
    }

    public void setFitnessClassId(int fitnessClassId) {
        this.fitnessClassId = fitnessClassId;
    }

    public int getGymLocationId() {
        return gymLocationId;
    }

    public void setGymLocationId(int gymLocationId) {
        this.gymLocationId = gymLocationId;
    }
}
