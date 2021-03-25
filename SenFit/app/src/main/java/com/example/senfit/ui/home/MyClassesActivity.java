/*Portia Siddiqua 107741175*/

package com.example.senfit.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.FitnessClass;
import com.example.senfit.dataContext.entities.GymClass;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.entities.Trainer;
import com.example.senfit.ui.inperson.InpersonClassData;
import com.example.senfit.ui.online.OnlineClassAdapter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MyClassesActivity extends AppCompatActivity {


    protected RecyclerView mRecyclerView;

    protected MyClassesAdapter mAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myclasses);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewMyClasses);

        mLayoutManager = new LinearLayoutManager(this);

        loadOnlineClass();


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

                    if (!onlineClass.isEnrolled()) continue;

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


                List<GymClass> gymClasses = DatabaseClient.getInstance()
                        .getAppDatabase()
                        .getGymClassDao()
                        .getGymClasses();

                for(GymClass gymClass: gymClasses) {

                    if (!gymClass.getEnrolled()) continue;

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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new MyClassesAdapter(dataSet);

                        mRecyclerView.setAdapter(mAdapter);

                        mRecyclerView.setAdapter(mAdapter);

                        mRecyclerView.setLayoutManager(mLayoutManager);
                    }
                });

                return null;
            }
        }.execute();
    }

}
