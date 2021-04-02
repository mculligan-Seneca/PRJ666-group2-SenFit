package com.example.senfit.dataContext.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.senfit.dataContext.entities.GymLocation;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface GymLocationDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    public Completable insertGymLocations(GymLocation...gymLocations);
}
