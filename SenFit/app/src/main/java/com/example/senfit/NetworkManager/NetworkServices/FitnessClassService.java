/*
PRJ666 Sen-Fit
init date: April 2nd 2021
Author Mitchell Culligan
Version 1.0
FitnessClassService
This interface uses retrofit to create a service that interacts with Sen-fit api for fitness class data
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.FitnessClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FitnessClassService {
    @GET("/fitnessClasses")
    public Call<List<FitnessClass>> getFitnessClasses();
}
