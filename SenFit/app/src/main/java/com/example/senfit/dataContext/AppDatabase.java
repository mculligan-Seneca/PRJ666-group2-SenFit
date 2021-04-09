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

package com.example.senfit.dataContext;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.senfit.dataContext.dao.CovidLogDAO;
import com.example.senfit.dataContext.dao.ExerciseDAO;
import com.example.senfit.dataContext.dao.FitnessClassDAO;
import com.example.senfit.dataContext.dao.FitnessPortfolioDAO;
import com.example.senfit.dataContext.dao.FitnessResultDAO;
import com.example.senfit.dataContext.dao.GymClassDAO;
import com.example.senfit.dataContext.dao.GymLocationDAO;
import com.example.senfit.dataContext.dao.MemberDAO;
import com.example.senfit.dataContext.dao.OnlineClassDAO;
import com.example.senfit.dataContext.dao.TrainerDAO;
import com.example.senfit.dataContext.dao.UnregisteredClientDAO;
import com.example.senfit.dataContext.entities.CovidLog;
import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.dataContext.entities.FitnessResult;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.GymLocation;
import com.example.senfit.dataContext.entities.Member;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.entities.Trainer;
import com.example.senfit.dataContext.entities.TrainingExercise;
import com.example.senfit.dataContext.entities.TrainingPlan;
import com.example.senfit.dataContext.entities.TrainingSession;
import com.example.senfit.dataContext.entities.UnregisteredClient;
import com.example.senfit.dataContext.views.FitnessResultView;
import com.example.senfit.dataContext.views.OnlineClassView;

@Database(entities={Member.class, Trainer.class, CovidLog.class,
    Exercise.class, FitnessClass.class, FitnessPortfolio.class, GymClass.class, GymLocation.class,
        OnlineClass.class, TrainingExercise.class, TrainingPlan.class, TrainingSession.class,FitnessResult.class, UnregisteredClient.class}
        ,views={FitnessResultView.class, OnlineClassView.class},version=13,
exportSchema=false)//See what export schema is used for

@TypeConverters({Converter.class})
//TODO: Add Entity classes to database annotation
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemberDAO getMemberDao();

    public abstract TrainerDAO getTrainerDao();

    public abstract GymClassDAO getGymClassDao();


    public abstract FitnessClassDAO getFitnessClassDao();

    public abstract OnlineClassDAO getOnlineClassDao();



    public abstract CovidLogDAO getCovidLogDAO();

    public abstract FitnessPortfolioDAO getFitnessPortfolioDAO();


    public abstract FitnessResultDAO getFitnessResultDao();

    public abstract ExerciseDAO getExerciseDao();

    public abstract UnregisteredClientDAO getUnregisteredClientDAO();

    public abstract GymLocationDAO getGymLocationDAO();


    //TODO: Add any new DAO's to appdatabase
}
