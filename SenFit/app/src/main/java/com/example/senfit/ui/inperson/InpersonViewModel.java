/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.ui.inperson;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.GymClassService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.dao.GymClassDAO;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.InPersonClass;
import com.example.senfit.dataContext.entities.Trainer;

import org.json.JSONObject;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InpersonViewModel extends ViewModel {
    private MutableLiveData<List<InpersonClassData>> inpersonClasses;
    private GymClassService gymClassService;
    private DatabaseClient dbClient;
    public InpersonViewModel(){
        this.inpersonClasses = null;
        this.gymClassService = NetworkManager.getNetworkManager().createNetworkService(GymClassService.class);
        this.dbClient= DatabaseClient.getInstance();
    }
    public LiveData<List<InpersonClassData>> getInpersonClasses() {
        if (inpersonClasses == null) {
            this.inpersonClasses= new MutableLiveData<List<InpersonClassData>>();
            Call<List<GymClass>> gymClassCall = this.gymClassService.getGymClasses();
            gymClassCall.enqueue(new Callback<List<GymClass>>() {
                @Override
                public void onResponse(Call<List<GymClass>> call, Response<List<GymClass>> response) {
                    if(!response.isSuccessful()){

                        try{
                            Log.e("in_person_class",response.errorBody().string());
                        }catch(Exception e){
                            Log.e("in_person_class",e.getMessage());
                        }
                    }else{
                        List<GymClass> gymClasses = response.body();
                        GymClass[] classes = new GymClass[1];
                        classes= gymClasses.toArray(classes);
                        dbClient.getAppDatabase()
                                .getGymClassDao()
                                .insertGymClasses(classes)
                                .subscribeOn(Schedulers.io())
                                .subscribe(new CompletableObserver() {
                                    private Disposable disposable;
                                    @Override
                                    public void onSubscribe(@NonNull Disposable d) {
                                        disposable=d;
                                    }

                                    @Override
                                    public void onComplete() {

                                        loadInpersonClass();
                                        if(!disposable.isDisposed())
                                            disposable.dispose();
                                    }

                                    @Override
                                    public void onError(@NonNull Throwable e) {
                                        Log.e("in_person_class",e.getMessage());
                                    }
                                });


                    }
                }

                @Override
                public void onFailure(Call<List<GymClass>> call, Throwable t) {
                    Log.e("in_person_class",t.getMessage());
                }
            });

        }
        return inpersonClasses;
    }


    /*
    Fetching inperson class related data from the DB
     */
   private void loadInpersonClass() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<GymClass> gymClasses = DatabaseClient.getInstance()
                        .getAppDatabase()
                        .getGymClassDao()
                        .getGymClasses();

                List<InpersonClassData> dataSet = new ArrayList<>();

                for(GymClass gymClass: gymClasses) {
                    InpersonClassData data = new InpersonClassData();
                    data.setGymClassId(gymClass.getGymClassId());
                    FitnessClass fClass = DatabaseClient
                            .getInstance()
                            .getAppDatabase()
                            .getFitnessClassDao()
                            .getFitnessClass(gymClass.getFitnessClassId());
                    data.setClasName(fClass.getFitnessClassName());
                    data.setDate(gymClass.getClassDate().toString());
                    data.setEnrolled(gymClass.getEnrolled());

                    Date startDate = new Date(gymClass.getStartTime().getTime());
                    Calendar startCalendar = GregorianCalendar.getInstance();
                    startCalendar.setTime(startDate);

                    Date endDate = new Date(gymClass.getEndTime().getTime());
                    Calendar endCalendar = GregorianCalendar.getInstance();
                    endCalendar.setTime(endDate);

                    data.setTime(startCalendar.get(Calendar.HOUR_OF_DAY) + ":" + startCalendar.get(Calendar.MINUTE) + "-"
                            +endCalendar.get(Calendar.HOUR_OF_DAY) + ":" + endCalendar.get(Calendar.MINUTE));

                    Trainer trainer = DatabaseClient
                            .getInstance()
                            .getAppDatabase()
                            .getTrainerDao()
                            .getTrainer(gymClass.getTrainerId());
                    data.setInstructorName(trainer.getFirstName() + " " + trainer.getLastName());
                    dataSet.add(data);
                }

                inpersonClasses.postValue(dataSet);

                return null;
            }
        }.execute();
    }
}