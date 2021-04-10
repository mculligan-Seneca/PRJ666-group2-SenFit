/*
PRJ666 Sen-Fit
init date: Feb 22nd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessPortfolioViewModel
This viewModel class holds the data for the add fitness portfolio use case.
 */
package com.example.senfit.fitnessPortfolio.addFitnessPortfolio;

import android.util.Log;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.FitnessPortfolioService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessPortfolio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFitnessPortfolioViewModel extends ViewModel {

    private static final int EXERCISE_NUM=5;


    private FitnessPortfolio portfolio;
    private MutableLiveData<Long> rowNumData;
    private FitnessPortfolioService portfolioService;
    public AddFitnessPortfolioViewModel(){

        this.portfolio= new FitnessPortfolio();
        this.rowNumData= new MutableLiveData<>(null);
        this.portfolioService= NetworkManager.getNetworkManager().createNetworkService(FitnessPortfolioService.class);
    }

  public FitnessPortfolio getPortfolio(){
        return this.portfolio;
  }


  public void insertPortfolio(){
       Call<FitnessPortfolio> portfolioCall = this.portfolioService.createPortfolio(this.portfolio);
       portfolioCall.enqueue(new Callback<FitnessPortfolio>() {
           @Override
           public void onResponse(Call<FitnessPortfolio> call, Response<FitnessPortfolio> response) {
               if(response.isSuccessful()){
                   DatabaseClient.dbExecutors.execute(()->{

                       Long rowNum = DatabaseClient.getInstance()
                               .getAppDatabase()
                               .getFitnessPortfolioDAO()
                               .insertPortfolio(portfolio);

                       rowNumData.postValue(rowNum);
                   });
               }
           }

           @Override
           public void onFailure(Call<FitnessPortfolio> call, Throwable t) {
                    Log.e("load_portfolio",t.getMessage());
           }
       });

  }

  public LiveData<Long> getRowNumData(){
        return this.rowNumData;
  }
}
