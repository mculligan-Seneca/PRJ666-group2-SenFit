/*author: Portia siddiqua(107741175)*/

package com.example.senfit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GymClassDao {

    @Insert
    void insertGymClass(GymClass gymClass);

    @Query("Select * from GymClass")
    List<GymClass> getGymClasses();
}
