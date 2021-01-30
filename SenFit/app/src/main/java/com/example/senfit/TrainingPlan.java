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
    private String startDate;

    @ColumnInfo(name="fitnessPortfolioId")
    private int fitnessPortfolioId;//TODO: add foreignKey

    @ColumnInfo(name="trainerId")
    private int trainerId; //TODO: add foreignKey

    public int getTrainingPlanId() {
        return trainingPlanId;
    }

    public void setTrainingPlanId(int trainingPlanId) {
        this.trainingPlanId = trainingPlanId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getFitnessPortfolioId() {
        return fitnessPortfolioId;
    }

    public void setFitnessPortfolioId(int fitnessPortfolioId) {
        this.fitnessPortfolioId = fitnessPortfolioId;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }
}
