/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessResultViewModel
This viewmodel class holds the fitness result data when a user is performing the exercises
 */
package com.example.senfit.fitnessResult.addFitnessResults;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessResult;
import com.example.senfit.fitnessResult.ExerciseWithReps;

import java.util.LinkedList;
import java.util.List;

public class AddFitnessResultViewModel extends ViewModel {

    private static final int EXERCISE_NUM=5;//number of exercises to be performed
    private static final int REP_NUM=5;
    private int portfolioId;
    private DatabaseClient dbClient;

    private MediatorLiveData<List<Exercise>> exerciseList;
    private MutableLiveData<ExerciseWithReps> exerciseLiveData;
    private List<FitnessResult> completedList;
    private MutableLiveData<Integer> index;
    private MutableLiveData<Integer> size;

    public AddFitnessResultViewModel(int portfolioId){

        this.portfolioId=portfolioId;
        this.dbClient= DatabaseClient.getInstance();
        this.exerciseList = new MediatorLiveData<>();
        this.completedList= new LinkedList<FitnessResult>();
        this.size= new MutableLiveData<>(0);
        this.exerciseList.addSource(this.dbClient
                .getAppDatabase()
                .getExerciseDao()
                .getExercises(),list->{
            if(list.size()>EXERCISE_NUM){
                exerciseList.setValue(list.subList(0,EXERCISE_NUM));
                size.setValue(EXERCISE_NUM);
            }
            else{
                exerciseList.setValue(list);
                size.setValue(list.size());
            }
            //TODO:remove source
        });
        this.index=new MutableLiveData<>(0);
        this.exerciseLiveData=new MutableLiveData<>();
        this.exerciseList.addSource(this.index,position->{
            if(position<exerciseList.getValue().size()){
                exerciseLiveData.setValue(new ExerciseWithReps(exerciseList.getValue().get(position),REP_NUM));
            }
        });



    }


    public LiveData<ExerciseWithReps> getExerciseLiveData(){
        return this.exerciseLiveData;
    }

    public boolean hasNext(){//returns true if the user has more exercises to complete
        return this.index.getValue()<this.size.getValue();
    }

    public void addResult(int heartBeats){
        ExerciseWithReps e = this.exerciseLiveData.getValue();
        if(e!=null){
            FitnessResult result = new FitnessResult(portfolioId,e.exercise.getExerciseId(),e.reps,heartBeats);
            this.completedList.add(result);
            this.index.setValue(this.index.getValue()+1);
        }
    }

    public void insert(){
        DatabaseClient.dbExecutors.execute(()->{
            FitnessResult[] results = new FitnessResult[1];
            dbClient.getAppDatabase()
                    .getFitnessResultDao()
                    .insertResults(completedList.toArray(results));
        });
    }
    public int getPortfolioId(){
        return this.portfolioId;
    }
}
