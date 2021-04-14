package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.dataContext.entities.TrainingPlan;
import com.example.senfit.dataContext.views.TrainingPlanView;

import java.util.List;

@Dao
public interface TrainingPlanDAO {



    @Insert
    public void insertTrainingPlan(TrainingPlan plan);

    @Query("Select * from trainingplans where member_id=:memberId")
    public LiveData<List<TrainingPlan>> getTrainingPlans(int memberId);

    @Query("Select * from trainingPlanView where member_id=:id")
    public LiveData<List<TrainingPlanView>> getTrainingPlansForMember(int id);
}
