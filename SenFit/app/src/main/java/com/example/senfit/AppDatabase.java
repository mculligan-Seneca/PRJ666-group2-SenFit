/*
PRJ666 Sen-Fit
init date: January 24th 2021
Most recent Change:Feb 1st 2021
Author Mitchell Culligan
Version 1.0
AppDatabase class
This abstract base class repersents the database where our tables our stored and objects
can be accessed via DAO's
Changed: Added converter class to handle dates being stored
 */

package com.example.senfit;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities={Member.class,Trainer.class,CovidLog.class,
    Exercise.class, FitnessClass.class, FitnessPortfolio.class,GymClass.class,GymLocation.class,
        OnlineClass.class,TrainingExercise.class,TrainingPlan.class,TrainingSession.class},version=1)//See what export schema is used for
@TypeConverters({Converter.class})
//TODO: Add Entity classes to database annotation
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemberDAO getMemberDao();

    public abstract TrainerDAO getTrainerDao();

    //TODO: Add any new DAO's to appdatabase
}
