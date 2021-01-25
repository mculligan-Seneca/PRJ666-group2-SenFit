/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
TrainingPlan class
 */
package com.example.senfit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName="TrainingPlans")
public class TrainingPlan {
//TODO: add constructor and getter and setters
    @PrimaryKey(autoGenerate=true)
    private int trainingPlanId;

    @ColumnInfo(name="plan_name")
    private String planName;

    @ColumnInfo(name="start_date")
    private Date startDate;

    @ColumnInfo(name="fitnessPortfolioId")
    private int fitnessPortfolioId;//TODO: add foreignKey

    @ColumnInfo(name="trainerId")
    private int trainerId; //TODO: add foreignKey
}
