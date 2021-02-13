/*
PRJ666 Sen-Fit
init date: feb 12th 2021
Author Mitchell Culligan
Version 1.0
com.example.senfit.booked in person class

 */
package com.example.senfit.dataContext.entities;

import androidx.room.ColumnInfo;

import java.sql.Date;
import java.sql.Timestamp;

public class InPersonClass {
    @ColumnInfo(name="gymClassId")
    private int gymClassId;
    @ColumnInfo(name="date")
    private Date date;
    @ColumnInfo(name="startTime")
    private Timestamp startTime;
    @ColumnInfo(name="endTime")
    private Timestamp endTime;
    @ColumnInfo(name="className")
    private  String className;
    @ColumnInfo(name="trainerName")
    private String trainerName;


    public int getGymClassId() {
        return gymClassId;
    }

    public void setGymClassId(int gymClass) {
        this.gymClassId = gymClass;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }
}
