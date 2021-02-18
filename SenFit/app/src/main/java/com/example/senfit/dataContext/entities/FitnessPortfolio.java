/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
Fitness Portfolio Class
 */
package com.example.senfit.dataContext.entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName="fitnessPortfolio",foreignKeys = @ForeignKey(entity = Member.class,
        parentColumns = "member_id",
        childColumns = "member_id"))
public class FitnessPortfolio {
    @PrimaryKey(autoGenerate=true)
    private int fitnessPortfolioId;

    @ColumnInfo(name="session_duration")
    private int sessDuration;//the duration of a session in minutes

    @ColumnInfo(name="height")
    private float height;//height in ft

    @ColumnInfo(name="weight")
    private float weight;//weight in lbs
    @ColumnInfo(name="date_created")
    private Date dateCreated;

    @ColumnInfo(name="health_concerns")
    private String healthConcerns;

    @ColumnInfo(name="member_id")
    private int memberId;

    public int getFitnessPortfolioId() {
        return fitnessPortfolioId;
    }

    public void setFitnessPortfolioId(int fitnessPortfolioId) {
        this.fitnessPortfolioId = fitnessPortfolioId;
    }

    public int getSessDuration() {
        return sessDuration;
    }

    public void setSessDuration(int sessDuration) {
        this.sessDuration = sessDuration;
    }



    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getHealthConcerns() {
        return healthConcerns;
    }

    public void setHealthConcerns(String healthConcerns) {
        this.healthConcerns = healthConcerns;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
