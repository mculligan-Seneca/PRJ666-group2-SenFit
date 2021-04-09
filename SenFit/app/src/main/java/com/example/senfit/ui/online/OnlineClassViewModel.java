/*Author : Portia Siddiqua 107741175*/

package com.example.senfit.ui.online;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.OnlineClassService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.dao.OnlineClassDAO;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.entities.Trainer;
import com.example.senfit.dataContext.views.OnlineClassView;
import com.example.senfit.ui.inperson.InpersonClassData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnlineClassViewModel extends ViewModel {

    private LiveData<List<OnlineClassView>> mOnlineClasses;
    private OnlineClassService onlineClassService;
    private OnlineClassDAO onlineDAO;

    public OnlineClassViewModel(){
        this.onlineDAO=DatabaseClient.getInstance().getAppDatabase().getOnlineClassDao();
        this.onlineClassService= NetworkManager.getNetworkManager().createNetworkService(OnlineClassService.class);
        this.mOnlineClasses=this.onlineDAO.getOnlineClassView();
        Call<List<OnlineClass>> onlineCall= this.onlineClassService.getOnlineClasses();
        onlineCall.enqueue(new Callback<List<OnlineClass>>() {
            @Override
            public void onResponse(Call<List<OnlineClass>> call, Response<List<OnlineClass>> response) {
                if(!response.isSuccessful()){

                    try{
                        Log.e("in_person_class",response.errorBody().string());
                    }catch(Exception e){
                        Log.e("in_person_class",e.getMessage());
                    }
                }else{
                    List<OnlineClass> onlineClassList =response.body();

                    Observable.fromIterable(onlineClassList)
                            .subscribeOn(Schedulers.io())
                            .observeOn(Schedulers.from(DatabaseClient.dbExecutors))
                            .subscribe(new Observer<OnlineClass>() {
                                private Disposable disposable;
                                @Override
                                public void onSubscribe(@NonNull Disposable d) {
                                    disposable=d;
                                }

                                @Override
                                public void onNext(@NonNull OnlineClass onlineClass) {
                                    try {
                                        onlineDAO.insertOnlineClass(onlineClass);
                                    }catch(SQLiteConstraintException sqle){
                                        Log.e("load_online_data",sqle.getMessage());
                                    }
                                }

                                @Override
                                public void onError(@NonNull Throwable e) {
                                    Log.e("load_online_data",e.getMessage());
                                }

                                @Override
                                public void onComplete() {
                                    if(!disposable.isDisposed())
                                        disposable.dispose();;
                                }
                            });

                }
            }

            @Override
            public void onFailure(Call<List<OnlineClass>> call, Throwable t) {
                Log.e("load_online_classes",t.getMessage());
               // loadOnlineClass();
            }
        });
    }

    public LiveData<List<OnlineClassView>> getOnlineClasses() {

        return this.mOnlineClasses;
    }

    /*
    private void loadOnlineClass() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<OnlineClass> onlineClasses = DatabaseClient.getInstance()
                        .getAppDatabase()
                        .getOnlineClassDao()
                        .getOnlineClasses();

                List<InpersonClassData> dataSet = new ArrayList<>();

                for(OnlineClass onlineClass: onlineClasses) {
                    InpersonClassData data = new InpersonClassData();
                    data.setGymClassId(onlineClass.getOnlineClassId());
                    FitnessClass fClass = DatabaseClient
                            .getInstance()
                            .getAppDatabase()
                            .getFitnessClassDao()
                            .getFitnessClass(onlineClass.getFitnessClassId());
                    data.setClasName(fClass.getFitnessClassName());
                    data.setDate(onlineClass.getClassDate().toString());
                    data.setEnrolled(false);

                    Date startDate = new Date(onlineClass.getStartTime().getTime());
                    Calendar startCalendar = GregorianCalendar.getInstance();
                    startCalendar.setTime(startDate);

                    Date endDate = new Date(onlineClass.getEndTime().getTime());
                    Calendar endCalendar = GregorianCalendar.getInstance();
                    endCalendar.setTime(endDate);

                    data.setTime(startCalendar.get(Calendar.HOUR_OF_DAY) + ":" + startCalendar.get(Calendar.MINUTE) + "-"
                            +endCalendar.get(Calendar.HOUR_OF_DAY) + ":" + endCalendar.get(Calendar.MINUTE));

                    Trainer trainer = DatabaseClient
                            .getInstance()
                            .getAppDatabase()
                            .getTrainerDao()
                            .getTrainer(onlineClass.getTrainerId());
                    data.setInstructorName(trainer.getFirstName() + " " + trainer.getLastName());
                    dataSet.add(data);
                }

                mOnlineClasses.postValue(dataSet);


                return null;
            }
        }.execute();
    }

*/
}