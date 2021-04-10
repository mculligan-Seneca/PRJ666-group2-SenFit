package com.example.senfit.dataContext.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.dataContext.entities.TrainingPlan;

@Dao
public interface TrainingPlanDAO {


    @Insert
    public void insertWithPortfolio(TrainingPlan plan, FitnessPortfolio portfolio);
}
