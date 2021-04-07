package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.senfit.dataContext.entities.GymLocation;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface GymLocationDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Completable insertGymLocations(GymLocation...gymLocations);

    @Query("Select * from GymLocations")
    public LiveData<List<GymLocation>> getGymLocations();
}
