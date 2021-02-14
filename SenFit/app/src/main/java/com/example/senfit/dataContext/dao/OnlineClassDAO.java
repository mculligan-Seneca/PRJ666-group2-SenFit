package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.senfit.dataContext.entities.InPersonClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.OnlineClass;

import java.util.List;

@Dao
public interface OnlineClassDAO {

    @Insert
    void insertOnlineClass(OnlineClass onlineClass);

    @Transaction
    @Query("Select * from onlineClasses")
    public List<OnlineClass> getOnlineClasses();

//    @Query("UPDATE onlineClass SET enrolled=:enrolled where gymClassId=:id")
//    void updateEnrollStatus(int id, boolean enrolled);
}
