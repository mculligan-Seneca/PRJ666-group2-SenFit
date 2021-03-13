/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessResultViewModel
This viewmodel class holds the fitness result data when a user is performing the exercises
 */
package com.example.senfit.fitnessResult.addFitnessResults;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessResult;

import java.util.LinkedList;
import java.util.List;

public class AddFitnessResultViewModel extends ViewModel {

    private static final int EXERCISE_NUM=5;//number of exercises to be performed

    private int portfolioId;
    private DatabaseClient dbClient;

    private LiveData<List<Exercise>> exerciseList;

    private List<FitnessResult> completedList;

    private LiveData<Integer> size;

    public AddFitnessResultViewModel(int portfolioId){

        this.portfolioId=portfolioId;
        this.dbClient= DatabaseClient.getInstance();
        this.exerciseList = new MediatorLiveData<>();
        this.completedList= new LinkedList<FitnessResult>();
        this.size= new MutableLiveData<>(0);
        this.exerciseList= this.dbClient
                .getAppDatabase()
                .getExerciseDao()
                .getExercisesWithLim(EXERCISE_NUM);
        this.size=Transformations.map(this.exerciseList, List::size);//always set size to accurate list size






    }



    public LiveData<List<Exercise>> getExerciseList(){
        return this.exerciseList;
    }
    public boolean hasNext(){//returns true if the user has more exercises to complete
        List<Exercise> loaded= this.exerciseList.getValue();
        return  loaded!=null && this.completedList.size()<loaded.size();
    }

    public void addResult(FitnessResult result){

            this.completedList.add(result);
    }

    public int getIndex(){
        return this.completedList.size();
    }
    public void insert(){
        DatabaseClient.dbExecutors.execute(()->{
            FitnessResult[] results = new FitnessResult[1];
            try {
                results=completedList.toArray(results);
                dbClient.getAppDatabase()
                        .getFitnessResultDao()
                        .insertResults(results);
            }catch(Exception e){
                Log.e("insert_result_err",e.getMessage());
            }
        });
    }
    public int getPortfolioId(){
        return this.portfolioId;
    }
}
