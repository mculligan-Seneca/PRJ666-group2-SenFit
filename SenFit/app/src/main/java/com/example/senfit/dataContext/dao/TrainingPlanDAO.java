package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.dataContext.entities.TrainingPlan;

import java.util.List;

@Dao
public interface TrainingPlanDAO {


    @Insert
    public void insertWithPortfolio(TrainingPlan plan, FitnessPortfolio portfolio);

    @Query("Select * from TrainingPlans where member_id=:id")
    public LiveData<List<TrainingPlan>> getTrainingPlansForMember(int id);
}
