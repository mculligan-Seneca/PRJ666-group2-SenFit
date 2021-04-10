package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.FitnessPortfolio;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FitnessPortfolioService {

    @POST("/fitnessPortfolio")
    public Call<FitnessPortfolio> createPortfolio(@Body FitnessPortfolio portfolio);

    @GET("/member/{id}/fitnessPortfolios")
    public Call<List<FitnessPortfolio>> getPortfolios(@Path("id") int memberId);
}
