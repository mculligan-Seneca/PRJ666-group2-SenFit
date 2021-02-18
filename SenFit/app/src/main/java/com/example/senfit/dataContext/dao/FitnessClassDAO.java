/*author: Portia siddiqua(107741175)*/

package com.example.senfit.dataContext.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.senfit.dataContext.entities.FitnessClass;

import java.util.List;

@Dao
public interface FitnessClassDAO {

    @Insert
    long insertFitnessClass(FitnessClass member);

    @Query("Select * from FitnessClass where fitnessClassId=:id")
    public FitnessClass getFitnessClass(long id);

    @Query("Select * from fitnessClass")
    public List<FitnessClass> getAllClasses();

}
