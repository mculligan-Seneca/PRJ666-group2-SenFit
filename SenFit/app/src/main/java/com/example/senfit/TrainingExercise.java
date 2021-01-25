/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
TrainingExcercise class
 */
package com.example.senfit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="trainingExercises")
public class TrainingExercise {
    @PrimaryKey(autoGenerate = true)
    private int trainingExerciseID;//TODO: ADD constructors and getters and setter methods

    @ColumnInfo(name = "repNumber")
    private int repNumber; // the number of reps the member must accomplish

    @ColumnInfo(name="exerciseId")
    private int exerciseId;//TODO: add foreign key dependency
}
