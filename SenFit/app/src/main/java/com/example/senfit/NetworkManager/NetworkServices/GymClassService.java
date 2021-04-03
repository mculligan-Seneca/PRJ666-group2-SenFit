/*
PRJ666 Sen-Fit
init date: March 29th 2021
Author Mitchell Culligan
Version 1.0
GymClassService
This interface repersents a service that interacts with the Sen-FIt API that specializes in dealing with GymClass data.
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.GymClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GymClassService {
    @GET("/gymClasses")
    public Call<List<GymClass>> getGymClasses();
}
