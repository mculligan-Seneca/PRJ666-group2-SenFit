/*
PRJ666 Sen-Fit
init date:  2021
Author Mitchell Culligan
Version 1.0
Fitness Portfolio ViewModel factory
This factory class is responsible for creating a fitness portfolio viewmodel
 */
package com.example.senfit.fitnessPortfolio;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class PortfolioViewModelFactory implements ViewModelProvider.Factory {
   private static final String FACTORY_TAG="PORTFOLIO_FACTORY";

    private int memberId;

   public PortfolioViewModelFactory(int memberId){
       this.memberId=memberId;
   }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

       try {
           return modelClass.getConstructor(int.class).newInstance(memberId);
       }catch(ReflectiveOperationException rfe){
           Log.e(FACTORY_TAG, rfe.getMessage());
           throw new RuntimeException("Unable to create view model of "+modelClass);
       }
    }
}
