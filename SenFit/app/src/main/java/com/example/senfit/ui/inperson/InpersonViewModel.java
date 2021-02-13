/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.ui.inperson;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.InPersonClass;
import com.example.senfit.dataContext.entities.Trainer;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class InpersonViewModel extends ViewModel {
    private MutableLiveData<List<InpersonClassData>> inpersonClasses;





    public LiveData<List<InpersonClassData>> getInpersonClasses() {
        if (inpersonClasses == null) {
            inpersonClasses = new MutableLiveData<List<InpersonClassData>>();
            loadInpersonClass();
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

                    Date startDate = new Date(gymClass.getStartTime().getTime());
                    Date endDate = new Date(gymClass.getEndTime().getTime());

                    data.setTime(startDate.toString()/*startDate.getHours() + ":" + startDate.getMinutes() + "-"
                            + endDate.getHours() + ":" + endDate.getMinutes()*/);

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