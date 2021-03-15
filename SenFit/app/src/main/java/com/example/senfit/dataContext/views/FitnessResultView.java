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
        value = "select fitnessPortfolioId, e.exercise_name, reps_num, beatsPM "+
         "from fitnessresults join exercises e; "           )
public class FitnessResultView {


    public  int fitnessPortfolioId;
    @ColumnInfo(name="exercise_name")
    private String exerciseName;
    @ColumnInfo(name="reps_num")
    private int repsNum;
    private int beatsPM;


    public FitnessResultView(String exerciseName,int repsNum,int beatsPM) {
        this.exerciseName = exerciseName;
        this.repsNum=repsNum;
        this.beatsPM=beatsPM;
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
}
