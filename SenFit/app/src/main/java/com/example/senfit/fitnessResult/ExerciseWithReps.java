/*
PRJ666 Sen-Fit
init date: Feb 22nd 2021
Author Mitchell Culligan
Version 1.0
ExerciseWithReps
This class holds an exercise and the number of reps to be perform with said exercise
 */
package com.example.senfit.fitnessResult;

import com.example.senfit.dataContext.entities.Exercise;

public class ExerciseWithReps {

    public final Exercise exercise;

    public final int reps;

    public ExerciseWithReps(Exercise exercise,int reps){
        this.exercise=exercise;
        this.reps=reps;
    }
}
