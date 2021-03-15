/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
FitnessResultDAO interface
This interface is the data acess object for the fitness result entity.
 */
package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.senfit.dataContext.entities.FitnessResult;
import com.example.senfit.dataContext.views.FitnessResultView;

import java.util.List;

@Dao
public interface FitnessResultDAO {

    @Query("select * from fitnessResults where fitnessPortfolioId=:portfolioId")
    public List<FitnessResult> getResultsFromPortfolio(int portfolioId);

    @Insert
    public void insertResults(FitnessResult...results);


    @Query("Select * from fitnessresultview where fitnessPortfolioId=:portId")
    public LiveData<List<FitnessResultView>> getFitnessResultView(int portId);
}
