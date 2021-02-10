/*
PRJ666 Sen-Fit
init date: Febuary 1st 2021
Author Mitchell Culligan
Version 1.0
Covid Log DAO
This interface is the data access object for the covid logs class. All database operations will be defined here.
 */
package com.example.senfit.DataContext.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.senfit.DataContext.Entities.CovidLog;

import java.util.List;

@Dao
public  interface CovidLogDAO {

    @Insert
    public void insertCovidLog(CovidLog log);

    @Query("Select * from CovidLogs where member_id=:memberId")
    public LiveData<List<CovidLog>> getLogsForMember(int memberId);



}
