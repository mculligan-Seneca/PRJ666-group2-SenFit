/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
EnrollTrainingPlanViewModel
This is the viewmodel for the enroll training plan use case
 */
package com.example.senfit.trainingPlan;

import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.DatabaseClient;

public class EnrollTrainingPlanViewModel extends ViewModel {

    private int memberId;
    private int fitnessPortfolioId;
    private int gymLocationId;
    private int trainerId;
    private DatabaseClient dbClient;
    private

    public EnrollTrainingPlanViewModel(int memberId){
        this.memberId=memberId;
        this.dbClient = DatabaseClient.getInstance();


    }
}
