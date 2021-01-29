/*author: Portia siddiqua(107741175)*/

package com.example.senfit;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FitnessClassDao {

    @Insert
    long insertFitnessClass(FitnessClass member);

    @Query("Select * from FitnessClass where fitnessClassId=:id")
    public FitnessClass getClassName(long id);

}
