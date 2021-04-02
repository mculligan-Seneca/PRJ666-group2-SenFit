/*
PRJ666 Sen-Fit
init date: April 2nd 2021
Author Mitchell Culligan
Version 1.0
ExerciseService
This interface uses retrofit to create a service that interacts with Sen-fit api for exercises
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.Exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ExerciseService {
    @GET("/exercises")
    public Call<List<Exercise>> getExercises();
}

