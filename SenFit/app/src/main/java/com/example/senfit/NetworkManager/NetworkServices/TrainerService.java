/*
PRJ666 Sen-Fit
init date: April 2nd 2021
Author Mitchell Culligan
Version 1.0
TrainerService
This interface uses retrofit to create a service that interacts with Sen-fit api for trainer data
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.Trainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrainerService {

    @GET("/trainers")
    public Call<List<Trainer>> getTrainers();//TODO ensure gymlocations load first

}
