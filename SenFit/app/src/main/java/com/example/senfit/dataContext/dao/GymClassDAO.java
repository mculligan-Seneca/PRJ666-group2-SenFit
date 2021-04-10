/*author: Portia siddiqua(107741175)*/

package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.senfit.dataContext.entities.InPersonClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.views.InPersonView;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface GymClassDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public void insertGymClass(GymClass gymClass);
    @Insert

    public Completable insertGymClasses(GymClass...gymClasses);


    @Query("Select * from gymClass")
    public List<GymClass> getGymClasses();

    @Query("Select * from InPersonView")
    public LiveData<List<InPersonView>> getLiveInPersonClasses();

    @Query("UPDATE gymClass SET enrolled=:enrolled where gymClassId=:id")
    public void updateEnrollStatus(int id, boolean enrolled);

    @Query("Select * from gymClass")
    public Flowable<GymClass> getGymClassesFlow();
}
