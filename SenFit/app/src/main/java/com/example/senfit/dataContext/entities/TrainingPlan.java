/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
TrainingPlan class
 */
package com.example.senfit.dataContext.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

@Entity(tableName="TrainingPlans",foreignKeys = {@ForeignKey(entity = FitnessPortfolio.class,
        parentColumns = "fitnessPortfolioId",childColumns = "fitnessPortfolioId"),
            @ForeignKey(entity = Trainer.class,parentColumns = "trainerId",childColumns = "trainerId"),
            @ForeignKey(entity=Member.class,parentColumns = "member_id",childColumns = "member_id")})
public class TrainingPlan {
//TODO: add constructor and getter and setters
    @PrimaryKey
    @SerializedName("id")
    private int trainingPlanId;



    @ColumnInfo(name="start_date")
    private Date startDate;

    @ColumnInfo(name="fitnessPortfolioId")
    private int fitnessPortfolioId;//TODO: add foreignKey

    @ColumnInfo(name="trainerId")
    private int trainerId; //TODO: add foreignKey

    @ColumnInfo(name="member_id")
    private int member_id;

    @Ignore
    @SerializedName("fitnessPortfolio")
    private FitnessPortfolio portfolio;

    public TrainingPlan(){
        this.trainingPlanId=0;
        this.trainerId=0;
        this.member_id=0;
        this.startDate=null;
        this.fitnessPortfolioId=0;
        this.portfolio=null;
    }
    public int getTrainingPlanId() {
        return trainingPlanId;
    }

    public void setTrainingPlanId(int trainingPlanId) {
        this.trainingPlanId = trainingPlanId;
    }

    //TODO Add number of weeks

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
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

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public FitnessPortfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(FitnessPortfolio portfolio) {
        this.portfolio = portfolio;
    }
}
