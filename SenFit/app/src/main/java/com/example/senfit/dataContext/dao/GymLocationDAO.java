package com.example.senfit.dataContext.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.senfit.dataContext.entities.GymLocation;

import io.reactivex.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface GymLocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertGymLocations(GymLocation...gymLocations);
}
