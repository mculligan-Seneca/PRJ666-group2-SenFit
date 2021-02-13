/*
PRJ666 Sen-Fit
init date: feb 12th 2021
Author Mitchell Culligan
Version 1.0
com.example.senfit.booked in person class

 */
package com.example.senfit.dataContext.entities;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.sql.Date;
import java.sql.Timestamp;

public class InPersonClass {

    @Embedded
    private GymClass gymClass;

    @Relation(parentColumn = "fitnessClassId",entityColumn = "fitnessClassId")
    private FitnessClass fitnessClass;

    public FitnessClass getFitnessClass() {
        return fitnessClass;
    }

    public void setFitnessClass(FitnessClass fitnessClass) {
        this.fitnessClass = fitnessClass;
    }

    public GymClass getGymClass() {
        return gymClass;
    }

    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }
}
