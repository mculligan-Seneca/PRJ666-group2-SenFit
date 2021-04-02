package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.Trainer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TrainerService {

    @GET("/trainers")
    public Call<List<Trainer>> getTrainers();//TODO ensure gymlocations load first

}
