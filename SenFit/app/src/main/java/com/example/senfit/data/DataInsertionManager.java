package com.example.senfit.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.Trainer;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataInsertionManager {

    static DummyData data;

    private static class DummyData {
        List<FitnessClass> fitnessClasses = new ArrayList<>();
        List<Trainer> trainerList = new ArrayList<>();
        List<GymClass> gymClassList = new ArrayList<>();
    }

    private static void populateDummyData() {
        data = new DummyData();

        FitnessClass fitnessClass1 =  new FitnessClass();
        fitnessClass1.setFitnessClassName("Yoga");

        FitnessClass fitnessClass2 =  new FitnessClass();
        fitnessClass2.setFitnessClassName("Aerobic");

        FitnessClass fitnessClass3 =  new FitnessClass();
        fitnessClass3.setFitnessClassName("Cross Training");

        FitnessClass fitnessClass4 =  new FitnessClass();
        fitnessClass4.setFitnessClassName("Strength Training");

        data.fitnessClasses.add(fitnessClass1);
        data.fitnessClasses.add(fitnessClass2);
        data.fitnessClasses.add(fitnessClass3);
        data.fitnessClasses.add(fitnessClass4);

        Trainer trainer1 = new Trainer();
        trainer1.setEmail("a@gmail.com");
        trainer1.setPostalCode("M9A3J9");
        trainer1.setFirstName("Ellie");
        trainer1.setLastName("Mechham");

        Trainer trainer2 = new Trainer();
        trainer2.setEmail("a@gmail.com");
        trainer2.setPostalCode("M9A3J9");
        trainer2.setFirstName("Julie");
        trainer2.setLastName("Anderson");

        Trainer trainer3 = new Trainer();
        trainer3.setEmail("a@gmail.com");
        trainer3.setPostalCode("M9A3J9");
        trainer3.setFirstName("Paul");
        trainer3.setLastName("Smith");

        Trainer trainer4 = new Trainer();
        trainer4.setEmail("a@gmail.com");
        trainer4.setPostalCode("M9A3J9");
        trainer4.setFirstName("John");
        trainer4.setLastName("Austine");

        data.trainerList.add(trainer1);
        data.trainerList.add(trainer2);
        data.trainerList.add(trainer3);
        data.trainerList.add(trainer4);

        GymClass gymClass1 = new GymClass();
        gymClass1.setGymLocationId(1234);
        gymClass1.setClassDate(Date.valueOf("2021-02-12"));
        gymClass1.setStartTime(Timestamp.valueOf("2021-02-12 09:30:0"));
        gymClass1.setEndTime(Timestamp.valueOf("2021-02-12 10:30:0"));

        GymClass gymClass2 = new GymClass();
        gymClass2.setGymLocationId(1234);
        gymClass2.setClassDate(Date.valueOf("2021-02-13"));
        gymClass2.setStartTime(Timestamp.valueOf("2021-02-13 11:30:0"));
        gymClass2.setEndTime(Timestamp.valueOf("2021-02-13 12:30:0"));

        GymClass gymClass3 = new GymClass();
        gymClass3.setGymLocationId(1234);
        gymClass3.setClassDate(Date.valueOf("2021-02-14"));
        gymClass3.setStartTime(Timestamp.valueOf("2021-02-14 10:30:0"));
        gymClass3.setEndTime(Timestamp.valueOf("2021-02-14 11:30:0"));

        GymClass gymClass4 = new GymClass();
        gymClass4.setGymLocationId(1234);
        gymClass4.setClassDate(Date.valueOf("2021-02-15"));
        gymClass4.setStartTime(Timestamp.valueOf("2021-02-15 08:30:0"));
        gymClass4.setEndTime(Timestamp.valueOf("2021-02-15 09:30:0"));

        data.gymClassList.add(gymClass1);
        data.gymClassList.add(gymClass2);
        data.gymClassList.add(gymClass3);
        data.gymClassList.add(gymClass4);

    }

    private static Long getTime(String dateStr) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date startDate = null;
        try {
            startDate = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate.getTime();

    }


    public static void insertDummyData(Context context) {
       if (!getDummyDataInserted(context)) {
            populateDummyData();
            for (int i = 0 ; i < 4; i++) {
                insertTrainer(context, i);

            }




            setDummyDataInserted(context);
     }
    }

    static void insertTrainer(Context context, int pass) {
        final long[] trainerId = new long[1];
        Trainer trainer =  new Trainer();
        trainer.setEmail(data.trainerList.get(pass).getEmail());
        trainer.setFirstName(data.trainerList.get(pass).getFirstName());
        trainer.setLastName(data.trainerList.get(pass).getLastName());
        trainer.setPostalCode(data.trainerList.get(pass).getPostalCode());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                trainerId[0] = DatabaseClient.initDB(context).getAppDatabase().getTrainerDao().insertTrainer(trainer);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                insertFitnessClass(context, trainerId[0], pass);
                super.onPostExecute(aVoid);
            }
        }.execute();


    }


    static void insertFitnessClass(Context context, long trainerId, int pass) {

        final long[] classId = new long[1];

        FitnessClass fitnessClass = new FitnessClass();
        fitnessClass.setFitnessClassName(data.fitnessClasses.get(pass).getFitnessClassName());

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                classId[0] = DatabaseClient.initDB(context).getAppDatabase().getFitnessClassDao().insertFitnessClass(fitnessClass);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                insertInpersonDummyData(context, trainerId, classId[0], pass);
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

     static void insertInpersonDummyData(Context context, long traineId, long fitnessClassId, int pass) {
        GymClass gymClass = new GymClass();
        gymClass.setClassDate(data.gymClassList.get(pass).getClassDate());


         gymClass.setStartTime(data.gymClassList.get(pass).getStartTime());


         gymClass.setEndTime(data.gymClassList.get(pass).getEndTime());

        gymClass.setFitnessClassId(fitnessClassId);
        gymClass.setGymLocationId(data.gymClassList.get(pass).getGymLocationId());
        gymClass.setTrainerId(traineId);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.initDB(context).getAppDatabase().getGymClassDao().insertGymClass(gymClass);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
            }
        }.execute();
    }

    private static void setDummyDataInserted(Context context) {
        SharedPreferences sp = context.getSharedPreferences("senfit_pref", AppCompatActivity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("dummy_data_flag", 1);
        editor.commit();
    }

    private static boolean getDummyDataInserted(Context context) {
        SharedPreferences sp = context.getSharedPreferences("senfit_pref", AppCompatActivity.MODE_PRIVATE);
        int myIntValue = sp.getInt("dummy_data_flag", 0);
        return myIntValue > 0;
    }
}
