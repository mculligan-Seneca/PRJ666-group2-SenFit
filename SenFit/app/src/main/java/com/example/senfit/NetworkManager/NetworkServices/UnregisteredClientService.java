/*
PRJ666 Sen-Fit
init date: April 11th 2021
Author Mitchell Culligan
Version 1.0
UnregisteredClientService

 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.UnregisteredClient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface UnregisteredClientService {

    @GET("/registerClient")
    public Call<UnregisteredClient> registerClient(@Body UnregisteredClient client);
}
