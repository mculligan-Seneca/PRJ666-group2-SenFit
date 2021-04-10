/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
EnrollTrainingPlanViewModel
This is the viewmodel for the enroll training plan use case
 */
package com.example.senfit.trainingPlan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.dataContext.entities.GymLocation;
import com.example.senfit.dataContext.entities.Trainer;
import com.example.senfit.dataContext.entities.TrainingPlan;

import java.util.List;

public class EnrollTrainingPlanViewModel extends ViewModel {


    private TrainingPlan trainingPlan;
    private int gymLocationId;
    private DatabaseClient dbClient;
    private LiveData<List<FitnessPortfolio>> livePortfolioData;
    private LiveData<List<GymLocation>> gymLocationData;
    private LiveData<List<Trainer>> trainerData;
    public EnrollTrainingPlanViewModel(int memberId){
        this.trainingPlan=new TrainingPlan();
        this.trainingPlan.setMember_id(memberId);
        this.dbClient = DatabaseClient.getInstance();
        this.livePortfolioData= dbClient.getAppDatabase()
                .getFitnessPortfolioDAO()
                .getFitnessPortfolioFromMember(this.trainingPlan.getMember_id());
        this.gymLocationData = dbClient.getAppDatabase()
                .getGymLocationDAO()
                .getGymLocations();
        this.trainerData=null;

    }

    public LiveData<List<FitnessPortfolio>> getLivePortfolioData(){
        return this.livePortfolioData;
    }

    public LiveData<List<GymLocation>> getGymLocationData(){
        return this.gymLocationData;
    }

    public LiveData<List<Trainer>> getTrainerData(){
        if(this.trainerData==null)
            this.trainerData=this.dbClient.getAppDatabase()
                    .getTrainerDao()
                    .getTrainersFromGym(this.gymLocationId);
        return this.trainerData;
    }
    public int getMemberId(){
        return this.trainingPlan.getMember_id();
    }
    public void setFitnessPortfolioId(int fitnessPortfolioId){
        this.trainingPlan.setFitnessPortfolioId(fitnessPortfolioId);
    }

    public void setGymLocation(int gymLocationId){
        this.gymLocationId=gymLocationId;
    }

    public void setTrainer(int trainerId){
        this.trainingPlan.setTrainerId(trainerId);
    }

    public void submitPlan(){

    }
}
