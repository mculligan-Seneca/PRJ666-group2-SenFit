/*
PRJ666 Sen-Fit
init date: Febuary 1st 2021
Author Mitchell Culligan
Version 1.0
Covid survey activity
This activity is provided when the user attempts to access an activity that deals with
gym facilities. The user must fill out the form provided which will be stored in database.
This activity returns a result and and must be supplied a member id via an intent.
 */
package com.example.senfit.covidLog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.senfit.dataContext.entities.CovidLog;
import com.example.senfit.R;
import com.example.senfit.login.LoginActivity;
import com.example.senfit.login.LoginHelper;


public class CovidSurveyActivity extends AppCompatActivity {
    //public static final String MEMBER_ID_TAG="member_id_tag"; //tag to retrieve member id from intent
    public static final String CANCELED_RESULT ="error_result";//intent tag in the case the result is cancelled

    private static final String DIALOG_TITLE="Covid Positive message";

    private CovidLogViewModel logViewModel;
   // private  ActivityCovidSurveyBinding binding;
    private RecyclerView recyclerView;
    private SurveyAdapter adapter;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_survey);
      //  this.binding = DataBindingUtil.setContentView(this,R.layout.activity_covid_survey);
        //setContentView(R.layout.activity_covid_survey);
        this.logViewModel=null;
        Intent intent = getIntent();

        //int memberId = intent.getIntExtra(MEMBER_ID_TAG,-1);//retrieves member id
        int memberId = LoginHelper.getMemberId(this); // Retrieve member id from saved location

        if(memberId!=-1){
            this.recyclerView = findViewById(R.id.covid_questions);
            this.submit = findViewById(R.id.survey_submit);
            this.submit.setEnabled(false);
         this.logViewModel = new ViewModelProvider(this).get(CovidLogViewModel.class);


         this.logViewModel.setMemberIdData(memberId);
         this.logViewModel.getRecentMemberLogs().observe(this,(logs)->{
             if(!logs.isEmpty()){//retrieves only logs within valid time period.
                 CovidLog cLog = logs.get(0);//retrieves most recent log
                 if(!cLog.getStatus()){
                    if(DateHelper.compareToDate(cLog.getDate_logged())) {//checks if log was made today
                            setResult(Activity.RESULT_OK);
                            finish();//log was already deemed ok for today
                        //process can continue
                    }
                    else
                    {
                            createUI();//otherwise continue continue to fill covid log
                    }

                 }else{
                    //provides member with message to stay at home
                     // sets result to cancelled
                     setCanceledResult(R.string.covid_status_positive_mssg);
                     /*
                     Incase of member has log in database but has a positive covid status
                      */


                 }

             }
             else{
                 createUI();
             }
         });
        }else{
            setCanceledResult(R.string.member_not_found);//in case of member not found
        }

    }

    public void createUI(){
        String[] questions=null;
        if(this.logViewModel.getSurveyQuestions().isEmpty()) {
            questions = getResources().getStringArray(R.array.covid_questions);//retrieves the survey questions
            this.logViewModel.setQuestionList(questions);//stores the questions in view model
        }
        this.adapter = new SurveyAdapter(this,this.logViewModel.getSurveyQuestions());
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(this.adapter);


    }

    public void radioSwitch(View v){
       boolean status = this.submit.isEnabled();
       this.submit.setEnabled(!status);
    }

    public void submit(View v){ //triggied by onclick action

        boolean status=this.logViewModel.insertLog();//inserts the log into the database and submits to backend

        if(!status) {
            Toast.makeText(this,R.string.log_success_mssg,Toast.LENGTH_SHORT).show();
            setResult(Activity.RESULT_OK);
            finish();
        }
        else {
            setCanceledResult(R.string.covid_status_positive_mssg);
        }

    }



    public void setCanceledResult(int resId){
        Intent result = new Intent ();
        result.putExtra(CANCELED_RESULT,resId);
        setResult(Activity.RESULT_CANCELED,result);
        finish();
    }
}