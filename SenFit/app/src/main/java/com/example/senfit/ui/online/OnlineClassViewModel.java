/*Author : Portia Siddiqua 107741175*/

package com.example.senfit.ui.online;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.entities.Trainer;
import com.example.senfit.ui.inperson.InpersonClassData;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class OnlineClassViewModel extends ViewModel {

    private MutableLiveData<List<InpersonClassData>> mOnlineClasses;

    public LiveData<List<InpersonClassData>> getOnlineClasses() {
        if (mOnlineClasses == null) {
            mOnlineClasses = new MutableLiveData<List<InpersonClassData>>();
            loadOnlineClass();
        }
        return mOnlineClasses;
    }

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


}