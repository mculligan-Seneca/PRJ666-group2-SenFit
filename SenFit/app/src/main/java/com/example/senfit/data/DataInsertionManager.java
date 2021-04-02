package com.example.senfit.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.FitnessClassService;
import com.example.senfit.NetworkManager.NetworkServices.GymLocationService;
import com.example.senfit.NetworkManager.NetworkServices.TrainerService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.GymLocation;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.entities.Trainer;

import org.json.JSONObject;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataInsertionManager {

    static DummyData data;

    private static class DummyData {
        List<FitnessClass> fitnessClasses = new ArrayList<>();
        List<Trainer> trainerList = new ArrayList<>();
        List<GymClass> gymClassList = new ArrayList<>();
        List<OnlineClass> onlineClassList = new ArrayList<>();
    }

    private DatabaseClient dbClient;
    private NetworkManager networkManager;
    private GymLocationService gymLocationService;
    private FitnessClassService fitnessClassService;
    private TrainerService trainerService;
    private CompositeDisposable disposables;

    public DataInsertionManager(Context context){
        this.disposables = new CompositeDisposable();//resource cleanup
       this.dbClient = DatabaseClient.initDB(context);
       this.networkManager= NetworkManager.getNetworkManager();
       this.gymLocationService = networkManager.createNetworkService(GymLocationService.class);
       this.fitnessClassService = networkManager.createNetworkService(FitnessClassService.class);
       this.trainerService =networkManager.createNetworkService(TrainerService.class);
    }


    public void loadData(){
        Call<List<GymLocation>> locationCall = gymLocationService.getGymLocations();
        locationCall.enqueue(new Callback<List<GymLocation>>() {
            @Override
            public void onResponse(Call<List<GymLocation>> call, Response<List<GymLocation>> response) {
                if(!response.isSuccessful()){
                    String errMsg=null;
                    try {
                        JSONObject jsonErr = new JSONObject(response.errorBody().string());
                        errMsg=jsonErr.getString("errMsg");
                        Log.e("load_data_err",errMsg);
                        if(!disposables.isDisposed())
                            disposables.clear();
                    }catch(Exception e){
                        handleError(e);

                    }
                }else{
                    List<GymLocation> locations = response.body();
                    GymLocation[] gymLocations = new GymLocation[1];
                    gymLocations=locations.toArray(gymLocations);
                    dbClient.getAppDatabase()
                            .getGymLocationDAO()
                            .insertGymLocations(gymLocations)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.from(DatabaseClient.dbExecutors))
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onComplete() {

                                    insertFitnessClasses();
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    handleError(e);

                                }
                            });


                }
            }

            @Override
            public void onFailure(Call<List<GymLocation>> call, Throwable t) {
                        handleError(t);
            }
        });
    }

    public void insertFitnessClasses(){
        Call<List<FitnessClass>> locationCall = this.fitnessClassService.getFitnessClasses();
        locationCall.enqueue(new Callback<List<FitnessClass>>() {
            @Override
            public void onResponse(Call<List<FitnessClass> call, Response<List<FitnessClass>> response) {
                if(!response.isSuccessful()){
                    String errMsg=null;
                    try {
                        JSONObject jsonErr = new JSONObject(response.errorBody().string());
                        errMsg=jsonErr.getString("errMsg");
                        Log.e("load_data_err",errMsg);
                        if(!disposables.isDisposed())
                            disposables.clear();
                    }catch(Exception e){
                        handleError(e);

                    }
                }else{
                    List<FitnessClass> classes = response.body();
                    FitnessClass[] fitnessClassList = new FitnessClass[1];
                    fitnessClassList=classes.toArray(fitnessClassList);
                    dbClient.getAppDatabase()
                            .getFitnessClassDao()
                            .insertFitnessClass(fitnessClassList)
                            .subscribe(new CompletableObserver() {
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    disposables.add(d);
                                }

                                @Override
                                public void onComplete() {

                                    insertFitnessClasses();
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    handleError(e);

                                }
                            });


                }
            }

            @Override
            public void onFailure(Call<List<GymLocation>> call, Throwable t) {
                handleError(t);
            }
        });
    }
    public void handleError(Throwable t){
        Log.e("load_data_err",t.getMessage());
        if(!disposables.isDisposed())
            disposables.clear();
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









            DatabaseClient.dbExecutors.execute(()->{


            });
          /* Exercise[] exercises = {new Exercise("Push up","" +
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


           setDummyDataInserted(context);*/
        }
    }

    public
/*
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




*/
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
