/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
FitnessClass Class
 */

package com.example.senfit.dataContext.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="fitnessClass")
public class FitnessClass {
    @PrimaryKey(autoGenerate = true)
    private int fitnessClassId;

    @ColumnInfo(name="fitness_class_name")
    private String fitnessClassName;

    public int getFitnessClassId() {
        return fitnessClassId;
    }

    public void setFitnessClassId(int fitnessClassId) {
        this.fitnessClassId = fitnessClassId;
    }

    public String getFitnessClassName() {
        return fitnessClassName;
    }

    public void setFitnessClassName(String fitnessClassName) {
        this.fitnessClassName = fitnessClassName;
    }
}
