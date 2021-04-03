package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.GymLocation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GymLocationService {

    @GET("/gymLocations")
    public Call<List<GymLocation>> getGymLocations();
}
