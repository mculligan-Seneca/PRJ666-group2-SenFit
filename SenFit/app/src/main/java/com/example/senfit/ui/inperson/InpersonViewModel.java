/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.ui.inperson;

import android.database.sqlite.SQLiteConstraintException;
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
import com.example.senfit.dataContext.views.InPersonView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InpersonViewModel extends ViewModel {
    private LiveData<List<InPersonView>> inpersonClasses;
    private GymClassService gymClassService;
    private GymClassDAO gymClassDAO;
    public InpersonViewModel(){

        this.inpersonClasses = null;
        this.gymClassService = NetworkManager.getNetworkManager().createNetworkService(GymClassService.class);;


        this.gymClassService = NetworkManager.getNetworkManager().createNetworkService(GymClassService.class);
        this.gymClassDAO= DatabaseClient.getInstance().getAppDatabase().getGymClassDao();
        this.inpersonClasses= this.gymClassDAO.getLiveInPersonClasses();
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
                    Observable.fromIterable(gymClasses)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.from(DatabaseClient.dbExecutors))
                            .subscribe(new Observer<GymClass>() {
                               private Disposable disposable;
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    disposable=d;
                                }

                                @Override
                                public void onNext(@NonNull GymClass gymClass) {
                                       try {
                                           gymClassDAO.insertGymClass(gymClass);
                                       }catch(SQLiteConstraintException sqle){
                                           Log.e("load_gym_data",sqle.getMessage());
                                       }
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.e("load_online_data",e.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                    if(!disposable.isDisposed())
                                        disposable.dispose();
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

    public LiveData<List<InPersonView>> getInpersonClasses() {

        return inpersonClasses;
    }


    /*
    Fetching inperson class related data from the DB

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

     */
}