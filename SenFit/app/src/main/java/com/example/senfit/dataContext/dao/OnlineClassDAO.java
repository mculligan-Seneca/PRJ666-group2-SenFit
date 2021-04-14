package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.senfit.dataContext.entities.InPersonClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.views.OnlineClassView;

import java.util.List;

import io.reactivex.Completable;

@Dao
public interface OnlineClassDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOnlineClass(OnlineClass onlineClass);



    @Query("Select * from onlineClasses")
    public List<OnlineClass> getOnlineClasses();

    @Query("select * from onlineClassView")
    public LiveData<List<OnlineClassView>> getOnlineClassView();

    @Query("UPDATE onlineClasses SET enrolled=:enrolled where onlineClassId=:id")
    void updateEnrollStatus(int id, boolean enrolled);
}
