/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
Excercise class
 */
package com.example.senfit.dataContext.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="Exercises")
public class Exercise {

    //TODO: Create Constructor
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    private int exerciseId;

    @ColumnInfo(name="exercise_name")
    private String exerciseName;

    @ColumnInfo(name="exercise_description")
    private String exerciseDescription;

    public Exercise(){
        this.exerciseName="";
        this.exerciseDescription="";
        //this.exerciseId=-1; NEVER ASSIGN AUTO GENERATED KEY
    }

    public Exercise(String name, String desc){
        this.exerciseName=name;
        this.exerciseDescription=desc;

    }
    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }
}
