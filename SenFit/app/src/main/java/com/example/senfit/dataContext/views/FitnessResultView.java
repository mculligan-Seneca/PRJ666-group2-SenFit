/*
PRJ666 Sen-Fit
init date: march 12th 2021
Author Mitchell Culligan
Version 1.0
FitnessResultView
this view class repersents a database view for displaying a fitness result.
 */
package com.example.senfit.dataContext.views;


import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

@DatabaseView(viewName = "fitnessresultview",
        value = "select fitnessPortfolioId, exerciseId, exercise_name, reps_num, beatsPM "+
         "from fitnessresults join exercises  " +
                "using(exerciseId)")
public class FitnessResultView {


    private int fitnessPortfolioId;

    private int exerciseId;
    @ColumnInfo(name="exercise_name")
    private String exerciseName;
    @ColumnInfo(name="reps_num")
    private int repsNum;
    private int beatsPM;


    public FitnessResultView(int fitnessPortfolioId, int exerciseId,String exerciseName,int repsNum,int beatsPM) {
        this.fitnessPortfolioId=fitnessPortfolioId;
        this.exerciseId=exerciseId;
        this.exerciseName = exerciseName;
        this.repsNum=repsNum;
        this.beatsPM=beatsPM;
    }

    public int getFitnessPortfolioId(){
        return this.fitnessPortfolioId;
    }
    public void setFitnessPortfolioId(int fitnessPortfolioId){
        this.fitnessPortfolioId=fitnessPortfolioId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getRepsNum() {
        return repsNum;
    }

    public int getBeatsPM() {
        return beatsPM;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
