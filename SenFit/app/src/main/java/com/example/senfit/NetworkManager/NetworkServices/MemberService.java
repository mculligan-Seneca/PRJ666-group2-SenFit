/*
PRJ666 Sen-Fit
init date: April 12th 2021
Author Mitchell Culligan
Version 1.0
MemberService
This service is used for handling member based routes for the backend
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.OnlineClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MemberService {

    @GET("/member/{id}/gymClasses")
    public Call<List<GymClass>> getGymClassesForMember(@Path("id") int id);


    @GET("/member/{id}/onlineClasses")
    public Call<List<OnlineClass>> getOnlineClassesForMember(@Path("id") int id);
}
