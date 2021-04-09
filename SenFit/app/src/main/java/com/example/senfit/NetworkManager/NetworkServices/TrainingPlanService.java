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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TrainingPlanService {

    @POST("/trainingPlan")
    public Call<Object> createTrainingPlan(@Body TrainingPlan plan);

}

