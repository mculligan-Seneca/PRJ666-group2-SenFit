/*
PRJ666 Sen-Fit
init date: march 12th 2021
Author Mitchell Culligan
Version 1.0
FitnessResultView
this view class repersents a database view for displaying a fitness result.
 */
package com.example.senfit.dataContext.views;


import androidx.room.DatabaseView;

@DatabaseView(viewName = "fitnessresultview",value = "")
public class FitnessResultView {

    private String exerciseName;
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
