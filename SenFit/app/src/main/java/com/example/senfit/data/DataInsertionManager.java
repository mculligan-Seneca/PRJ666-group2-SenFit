package com.example.senfit.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.entities.Trainer;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataInsertionManager {

    static DummyData data;

    private static class DummyData {
        List<FitnessClass> fitnessClasses = new ArrayList<>();
        List<Trainer> trainerList = new ArrayList<>();
        List<GymClass> gymClassList = new ArrayList<>();
        List<OnlineClass> onlineClassList = new ArrayList<>();
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

        FitnessClass fitnessClass5 =  new FitnessClass();
        fitnessClass5.setFitnessClassName("Aerobic");

        FitnessClass fitnessClass6 =  new FitnessClass();
        fitnessClass6.setFitnessClassName("Yoga");

        FitnessClass fitnessClass7 =  new FitnessClass();
        fitnessClass7.setFitnessClassName("Mindfulness");

        FitnessClass fitnessClass8 =  new FitnessClass();
        fitnessClass8.setFitnessClassName("Cardio");

        data.fitnessClasses.add(fitnessClass1);
        data.fitnessClasses.add(fitnessClass2);
        data.fitnessClasses.add(fitnessClass3);
        data.fitnessClasses.add(fitnessClass4);
        data.fitnessClasses.add(fitnessClass5);
        data.fitnessClasses.add(fitnessClass6);
        data.fitnessClasses.add(fitnessClass7);
        data.fitnessClasses.add(fitnessClass8);

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

        Trainer trainer5 = new Trainer();
        trainer5.setEmail("h@gmail.com");
        trainer5.setPostalCode("M9A3J9");
        trainer5.setFirstName("Bonnie");
        trainer5.setLastName("Hoellin");

        Trainer trainer6 = new Trainer();
        trainer6.setEmail("f@gmail.com");
        trainer6.setPostalCode("M9A3J9");
        trainer6.setFirstName("Ruby");
        trainer6.setLastName("Franky");

        Trainer trainer7 = new Trainer();
        trainer7.setEmail("w@gmail.com");
        trainer7.setPostalCode("M9A3J9");
        trainer7.setFirstName("Shane");
        trainer7.setLastName("Warne");

        Trainer trainer8 = new Trainer();
        trainer8.setEmail("r@gmail.com");
        trainer8.setPostalCode("M9A3J9");
        trainer8.setFirstName("Robert");
        trainer8.setLastName("Gibson");


        data.trainerList.add(trainer1);
        data.trainerList.add(trainer2);
        data.trainerList.add(trainer3);
        data.trainerList.add(trainer4);
        data.trainerList.add(trainer5);
        data.trainerList.add(trainer6);
        data.trainerList.add(trainer7);
        data.trainerList.add(trainer8);

        GymClass gymClass1 = new GymClass();
        gymClass1.setGymLocationId(1234);
        gymClass1.setClassDate(Date.valueOf("2021-02-12"));
        gymClass1.setStartTime(Timestamp.valueOf("2021-02-12 09:30:0"));
        gymClass1.setEndTime(Timestamp.valueOf("2021-02-12 10:30:0"));
        gymClass1.setEnrolled(false);

        GymClass gymClass2 = new GymClass();
        gymClass2.setGymLocationId(1234);
        gymClass2.setClassDate(Date.valueOf("2021-02-13"));
        gymClass2.setStartTime(Timestamp.valueOf("2021-02-13 11:30:0"));
        gymClass2.setEndTime(Timestamp.valueOf("2021-02-13 12:30:0"));
        gymClass2.setEnrolled(false);

        GymClass gymClass3 = new GymClass();
        gymClass3.setGymLocationId(1234);
        gymClass3.setClassDate(Date.valueOf("2021-02-14"));
        gymClass3.setStartTime(Timestamp.valueOf("2021-02-14 10:30:0"));
        gymClass3.setEndTime(Timestamp.valueOf("2021-02-14 11:30:0"));
        gymClass3.setEnrolled(false);

        GymClass gymClass4 = new GymClass();
        gymClass4.setGymLocationId(1234);
        gymClass4.setClassDate(Date.valueOf("2021-02-15"));
        gymClass4.setStartTime(Timestamp.valueOf("2021-02-15 08:30:0"));
        gymClass4.setEndTime(Timestamp.valueOf("2021-02-15 09:30:0"));
        gymClass4.setEnrolled(false);

        data.gymClassList.add(gymClass1);
        data.gymClassList.add(gymClass2);
        data.gymClassList.add(gymClass3);
        data.gymClassList.add(gymClass4);


        OnlineClass onlineClass1 = new OnlineClass();
        onlineClass1.setClassDate(Date.valueOf("2021-04-12"));
        onlineClass1.setStartTime(Timestamp.valueOf("2021-04-12 09:30:0"));
        onlineClass1.setEndTime(Timestamp.valueOf("2021-04-12 10:30:0"));
        onlineClass1.setEnrolled(false);

        OnlineClass onlineClass2 = new OnlineClass();
        onlineClass2.setClassDate(Date.valueOf("2021-04-13"));
        onlineClass2.setStartTime(Timestamp.valueOf("2021-04-13 11:30:0"));
        onlineClass2.setEndTime(Timestamp.valueOf("2021-04-13 12:30:0"));
        onlineClass2.setEnrolled(false);

        OnlineClass onlineClass3 = new OnlineClass();
        onlineClass3.setClassDate(Date.valueOf("2021-04-14"));
        onlineClass3.setStartTime(Timestamp.valueOf("2021-04-14 10:30:0"));
        onlineClass3.setEndTime(Timestamp.valueOf("2021-02-14 11:30:0"));
        onlineClass3.setEnrolled(false);

        OnlineClass onlineClass4 = new OnlineClass();
        onlineClass4.setClassDate(Date.valueOf("2021-04-15"));
        onlineClass4.setStartTime(Timestamp.valueOf("2021-02-15 08:30:0"));
        onlineClass4.setEndTime(Timestamp.valueOf("2021-02-15 09:30:0"));
        onlineClass4.setEnrolled(false);

        data.onlineClassList.add(onlineClass1);
        data.onlineClassList.add(onlineClass2);
        data.onlineClassList.add(onlineClass3);
        data.onlineClassList.add(onlineClass4);

        //insert exercise data



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
           Exercise[] exercises = {new Exercise("Push up","" +
                   "a conditioning exercise performed in a prone position by raising and lowering the body with the straightening and bending of the arms while keeping the back straight " +
                   "and supporting the body on the hands and toes."),
                   new Exercise("Sit Up","Situps are classic abdominal exercises done " +
                           "by lying on your back and lifting your torso. " +
                           "They use your body weight to strengthen " +
                           "and tone the core-stabilizing abdominal muscles."),
                   new Exercise("forward lunge","Stand with feet hip-width apart," +
                           " engage your core, " +
                           "and take a big step backward. Activate your glutes as " +
                           "you bend front knee to lower your body so back knee lightly " +
                           "taps the floor while keeping upper body upright. " +
                           "Drive front heel into the floor to return to starting position. Repeat on the other side")
           };
           DatabaseClient.dbExecutors.execute(()->{
                DatabaseClient.initDB(context)
                        .getAppDatabase()
                        .getExerciseDao()
                        .insertExercises(exercises);
           });
            populateDummyData();
            for (int i = 0 ; i < 4; i++) {
                insertInpersonTrainer(context, i);

            }


           for (int i = 4 ; i < 8; i++) {
               insertOnlineTrainer(context, i);
           }


           setDummyDataInserted(context);
     }
    }

    static void insertInpersonTrainer(Context context, int pass) {
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
                insertInpersonFitnessClass(context, trainerId[0], pass);
                super.onPostExecute(aVoid);
            }
        }.execute();


    }

    static void insertOnlineTrainer(Context context, int pass) {
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
                insertOnlineFitnessClass(context, trainerId[0], pass);
                super.onPostExecute(aVoid);
            }
        }.execute();


    }




    static void insertInpersonFitnessClass(Context context, long trainerId, int pass) {

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


    static void insertOnlineFitnessClass(Context context, long trainerId, int pass) {

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
                insertOnlineDummyData(context, trainerId, classId[0], pass - 4);
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


    static void insertOnlineDummyData(Context context, long traineId, long fitnessClassId, int pass) {
        OnlineClass onlineClass = new OnlineClass();
        onlineClass.setClassDate(data.onlineClassList.get(pass).getClassDate());
        onlineClass.setStartTime(data.onlineClassList.get(pass).getStartTime());
        onlineClass.setEndTime(data.onlineClassList.get(pass).getEndTime());
        onlineClass.setFitnessClassId(fitnessClassId);
        onlineClass.setTrainerId(traineId);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.initDB(context).getAppDatabase().getOnlineClassDao().insertOnlineClass(onlineClass);
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
