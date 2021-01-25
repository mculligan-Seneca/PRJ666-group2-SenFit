/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
TrainerDAO class
This interface repersents the data access object of the trainer class
 */
package com.example.senfit;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TrainerDAO {

    @Query("Select * from trainers where trainerId=:id")
    public Trainer getTrainer(int id);

    @Query("Select * from trainers")
    public List<Trainer> getAllTrainers();//TODO: update return data to Flowable or LiveData if possible

    //TODO: ADD methods for retrieving associated data

}
