/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.ui.inperson;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.InPersonClass;

import java.util.List;

public class InpersonViewModel extends ViewModel {
    //private MutableLiveData<List<InpersonClassData>> inpersonClasses;
    private LiveData<List<InPersonClass>> gymClassLiveData;//retrieves all in person classes

    public InpersonViewModel(){
        this.gymClassLiveData = DatabaseClient
                .getInstance()
                .getAppDatabase()
                .getGymClassDao()
                .getInPersonClasses();//live data of gym classes

    }

    public LiveData<List<InPersonClass>> getGymClassLiveData(){
        return this.gymClassLiveData;
    }
  /*  public LiveData<List<InpersonClassData>> getInpersonClasses(Context context) {
        if (inpersonClasses == null) {
            inpersonClasses = new MutableLiveData<List<InpersonClassData>>();
            //loadInpersonClass(context);
        }
        return inpersonClasses;
    }
*/

    /*
    Fetching inperson class related data from the DB
     */
  /*  private void loadInpersonClass(Context context) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                List<GymClass> gymClasses = DatabaseClient.initDB(context)
                        .getAppDatabase()
                        .getGymClassDao()
                        .getGymClasses();

                List<InpersonClassData> dataSet = new ArrayList<>();

                for(GymClass gymClass: gymClasses) {
                    InpersonClassData data = new InpersonClassData();
                    FitnessClass fClass = DatabaseClient
                            .getInstance()
                            .getAppDatabase()
                            .FitnessGymClassDao()
                            .getClassName(gymClass.getFitnessClassId());
                    data.setClasName(fClass.getFitnessClassName());
                    data.setDate(gymClass.getClassDate().toString());

                    Date startDate = new Date(gymClass.getStartTime());
                    Date endDate = new Date(gymClass.getEndTime());

                    data.setTime(startDate.getHours() + ":" + startDate.getMinutes() + "-"
                            + endDate.getHours() + ":" + endDate.getMinutes());

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
    } */
}