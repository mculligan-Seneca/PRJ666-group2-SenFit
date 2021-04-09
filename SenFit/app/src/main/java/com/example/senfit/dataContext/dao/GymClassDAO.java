/*author: Portia siddiqua(107741175)*/

package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.senfit.dataContext.entities.InPersonClass;
import com.example.senfit.dataContext.entities.GymClass;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface GymClassDAO {

    @Insert
    public Completable insertGymClasses(GymClass...gymClasses);


    @Query("Select * from gymClass")
    public List<GymClass> getGymClasses();

    @Query("UPDATE gymClass SET enrolled=:enrolled where gymClassId=:id")
    public void updateEnrollStatus(int id, boolean enrolled);

    @Query("Select * from gymClass")
    public Flowable<GymClass> getGymClassesFlow();
}
