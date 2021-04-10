/*
PRJ666 Sen-Fit
init date: April 8th 2021
Author Mitchell Culligan
Version 1.0
TrainingPlanService
This interface is designed with the intention of interacting with Training plan data on the sen fit api
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.TrainingPlan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TrainingPlanService {

    @POST("/trainingPlan")
    public Call<TrainingPlan> createTrainingPlan(@Body TrainingPlan plan);

    @GET("/member/{id}/trainingPlans")
    public Call<List<TrainingPlan>> getTrainingPlans(@Path("id") int memberId);

}

