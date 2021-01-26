/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
Fitness Portfolio Class
 */
package com.example.senfit;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName="fitnessPortfolio")
public class FitnessPortfolio {
    @PrimaryKey(autoGenerate=true)
    private int fitnessPortfolioId;

    @ColumnInfo(name="session_duration")
    private int sessDuration;//the duration of a session in minutes

    @ColumnInfo(name="member_goals")
    private String memberGoals;

    @ColumnInfo(name="member_id")
    private int memberId; //TODO: add foreignKEY or embeded

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

    public String getMemberGoals() {
        return memberGoals;
    }

    public void setMemberGoals(String memberGoals) {
        this.memberGoals = memberGoals;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
