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
import java.util.List;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessPortfolio;

public class FitnessPortfolioViewModel {
    private LiveData<List<FitnessPortfolio>> portfolioData;//gets list of portfolios for member
    private DatabaseClient dbClient;

    public FitnessPortfolioViewModel(int memberId){
        this.dbClient= DatabaseClient.getInstance();
        this.portfolioData = this.dbClient
                .getAppDatabase()
                .getFitnessPortfolioDAO()
                .getFitnessPortfolioFromMember(memberId);
    }

    public LiveData<List<FitnessPortfolio>> getPortfolioData(){
        return this.portfolioData;
    }

}
