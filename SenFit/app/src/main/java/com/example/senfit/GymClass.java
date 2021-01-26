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
    private Date classDate;

    @ColumnInfo(name="start_time")
    private Timestamp startTime;

    @ColumnInfo(name="end_time")
    private Timestamp endTime;

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
