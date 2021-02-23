/*
PRJ666 Sen-Fit
init date: Feb 22nd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessPortfolioViewModel
This viewModel class holds the data for the add fitness portfolio use case.
 */
package com.example.senfit.fitnessPortfolio;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessPortfolio;

import java.util.List;

public class AddFitnessPortfolioViewModel extends ViewModel {

    private static final int EXERCISE_NUM=5;

    private LiveData<List<Exercise>> exerciseList;
    private FitnessPortfolio portfolio;
    private MutableLiveData<Long>rowNum;
    public final ObservableArrayList<Integer> resultList;
    public AddFitnessPortfolioViewModel(){

        this.portfolio= new FitnessPortfolio();
        this.resultList= new ObservableArrayList<>();
    }

  public FitnessPortfolio getPortfolio(){
        return this.portfolio;
  }


}
