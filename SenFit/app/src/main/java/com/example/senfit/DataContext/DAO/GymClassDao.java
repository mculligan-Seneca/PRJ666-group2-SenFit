/*author: Portia siddiqua(107741175)*/

package com.example.senfit.DataContext.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.senfit.DataContext.Entities.GymClass;

import java.util.List;

@Dao
public interface GymClassDao {

    @Insert
    void insertGymClass(GymClass gymClass);

    @Query("Select * from GymClass")
    List<GymClass> getGymClasses();
}
