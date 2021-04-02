package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.FitnessClass;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FitnessClassService {
    @GET("/fitnessClasses")
    public Call<List<FitnessClass>> getFitnessClasses();
}
