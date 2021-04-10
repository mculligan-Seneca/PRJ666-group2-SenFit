package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.senfit.dataContext.entities.FitnessPortfolio;

import java.util.List;

@Dao
public interface FitnessPortfolioDAO {

    @Query("Select * from fitnessPortfolio where member_id=:memberId order by date_created desc")
     public LiveData<List<FitnessPortfolio>> getFitnessPortfolioFromMember(int memberId);


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public Long insertPortfolio(FitnessPortfolio portfolio);
}
