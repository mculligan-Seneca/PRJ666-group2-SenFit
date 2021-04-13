package com.example.senfit.dataContext.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.senfit.dataContext.entities.Exercise;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ExerciseDAO {

    @Query("select * from exercises")
    public LiveData<List<Exercise>> getExercises();

    @Query("select * from exercises LIMIT :lim")
    public LiveData<List<Exercise>> getExercisesWithLim(int lim);

    @Insert
    public Completable insertExercises(Exercise...exercises);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public void insertExercises(List<Exercise> exercises);
}
