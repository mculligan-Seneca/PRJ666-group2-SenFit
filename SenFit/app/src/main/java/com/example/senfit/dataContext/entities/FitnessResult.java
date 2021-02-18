/*
PRJ666 Sen-Fit
init date: February 17th  2021
Author Mitchell Culligan
Version 1.0
FitnessPortfolioExercise Class
this class repersents fitness results for a fitness portfolio
 */

package com.example.senfit.dataContext.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;






@Entity(tableName = "fitness_exercise",primaryKeys = {"exerciseId","fitnessPortfolioId"},
        foreignKeys = {@ForeignKey(entity = FitnessPortfolio.class,
        parentColumns = "fitnessPortfolioId",
        childColumns = "fitnessPortfolioId"),
        @ForeignKey(entity=Exercise.class,
                parentColumns = "exerciseId",
                childColumns = "exerciseId")})
public class FitnessResult {


    @ColumnInfo(name="fitnessPortfolioId")
    public  int fitnessPortfolioId;


    @ColumnInfo(name="exerciseId")
    public int exerciseId;

    @ColumnInfo(name="reps_num")
    public int repsNum;

    @ColumnInfo(name="beatsPM")
    public int beatsPM;//beats per minute after exercise

    public FitnessResult(){

    }
    public FitnessResult(int portfolioId,int exerciseId,int reps,int beats){
        this.fitnessPortfolioId=portfolioId;
        this.exerciseId=exerciseId;
        this.repsNum=reps;
        this.beatsPM=beats;
    }



}
