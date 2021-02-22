/*
PRJ666 Sen-Fit
init date: Feb 17th 2021
Author Mitchell Culligan
Version 1.0
Fitness Portfolio ViewModel
This view model class holds the data for the fitness portfolio use case
 */
package com.example.senfit.fitnessPortfolio;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import java.util.List;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.dataContext.entities.FitnessResult;

public class FitnessPortfolioViewModel extends ViewModel {
    private LiveData<List<FitnessPortfolio>> portfolioData;//gets list of portfolios for member
   // private LiveData<List<FitnessResult>> resultData;// gets fitness results for data
    private DatabaseClient dbClient;

    public FitnessPortfolioViewModel(int memberId){
        this.dbClient= DatabaseClient.getInstance();
        this.portfolioData = this.dbClient
                .getAppDatabase()
                .getFitnessPortfolioDAO()
                .getFitnessPortfolioFromMember(memberId);

      /*  this.resultData = Transformations.switchMap(this.portfolioData,portfolio->{
            return dbClient.getAppDatabase()
                    .getFitnessResultDao()
                    .getResultsFromPortfolio(portfolio.getFitnessPortfolioId());
        })
*/


    }

    public LiveData<List<FitnessPortfolio>> getPortfolioData(){
        return this.portfolioData;
    }

}
