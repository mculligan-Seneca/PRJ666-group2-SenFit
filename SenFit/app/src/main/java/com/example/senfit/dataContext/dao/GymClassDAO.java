/*author: Portia siddiqua(107741175)*/

package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.senfit.dataContext.entities.InPersonClass;
import com.example.senfit.dataContext.entities.GymClass;

import java.util.List;

@Dao
public interface GymClassDAO {

    @Insert
    void insertGymClass(GymClass gymClass);

    @Query("select  g.gymClassId as gymClassId, g.class_date as date, g.start_time as startTime,g.end_time as endTime," +
            "c.fitness_class_name as className, t.first_name || ' ' || t.last_name as trainerName "+
            " from gymClass g INNER JOIN fitnessClass c on g.fitnessClassId=c.fitnessClassId " +
            "INNER JOIN Trainers t on g.trainerId=t.trainerId")
    public LiveData<List<InPersonClass>> getInPersonClasses();
}
