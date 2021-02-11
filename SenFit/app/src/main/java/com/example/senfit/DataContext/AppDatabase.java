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

package com.example.senfit.DataContext;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.senfit.DataContext.DAO.CovidLogDAO;
import com.example.senfit.DataContext.DAO.FitnessClassDao;
import com.example.senfit.DataContext.DAO.GymClassDao;
import com.example.senfit.DataContext.DAO.MemberDAO;
import com.example.senfit.DataContext.DAO.TrainerDAO;
import com.example.senfit.DataContext.Entities.CovidLog;
import com.example.senfit.DataContext.Entities.Exercise;
import com.example.senfit.DataContext.Entities.FitnessClass;
import com.example.senfit.DataContext.Entities.FitnessPortfolio;
import com.example.senfit.DataContext.Entities.GymClass;
import com.example.senfit.DataContext.Entities.GymLocation;
import com.example.senfit.DataContext.Entities.Member;
import com.example.senfit.DataContext.Entities.OnlineClass;
import com.example.senfit.DataContext.Entities.Trainer;
import com.example.senfit.DataContext.Entities.TrainingExercise;
import com.example.senfit.DataContext.Entities.TrainingPlan;
import com.example.senfit.DataContext.Entities.TrainingSession;

@Database(entities={Member.class, Trainer.class, CovidLog.class,
    Exercise.class, FitnessClass.class, FitnessPortfolio.class, GymClass.class, GymLocation.class,
        OnlineClass.class, TrainingExercise.class, TrainingPlan.class, TrainingSession.class},version=1,
exportSchema=false)//See what export schema is used for
@TypeConverters({Converter.class})
//TODO: Add Entity classes to database annotation
public abstract class AppDatabase extends RoomDatabase {
    public abstract MemberDAO getMemberDao();

    public abstract TrainerDAO getTrainerDao();

    public abstract GymClassDao getGymClassDao();

    public abstract FitnessClassDao FitnessGymClassDao();

    public abstract CovidLogDAO getCovidLogDAO();

    //TODO: Add any new DAO's to appdatabase
}
