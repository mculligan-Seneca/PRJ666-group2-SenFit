/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
DisplayFitnessResultsViewModel
This viewmodel class exposes fitness results to be displayed for a portfolio.
 */
package com.example.senfit.fitnessResult.displayFitnessResults;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.views.FitnessResultView;

public class DisplayFitnessResultsViewModel extends ViewModel {

    private int fitnessPortId;
    private LiveData<List<FitnessResultView>> resultList;
    //private DatabaseClient dbClient;

    public DisplayFitnessResultsViewModel(int fitnessPortId){
        this.fitnessPortId=fitnessPortId;
        this.resultList = DatabaseClient.getInstance()
                        .getAppDatabase()
                        .getFitnessResultDao()
                        .getFitnessResultView(this.fitnessPortId);

    }

    public LiveData<List<FitnessResultView>> getResultList(){
        return this.resultList;
    }

}
