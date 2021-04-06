/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
EnrollTrainingPlanViewModelFactory
This repersents the factory class for the enroll training plan view model
 */
package com.example.senfit.trainingPlan;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EnrollTrainingPlanViewModelFactory implements ViewModelProvider.Factory{
    private static final String TRAINING_PLAN_FACTORYTAG="plan_tag";
    private int memberId;


    public EnrollTrainingPlanViewModelFactory(int memberId){
        this.memberId=memberId;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return modelClass.getConstructor(int.class).newInstance(this.memberId);
        }catch(ReflectiveOperationException rfe){
            Log.e(TRAINING_PLAN_FACTORYTAG, rfe.getMessage());
            throw new RuntimeException("Unable to create view model of "+modelClass);
        }
    }
}
