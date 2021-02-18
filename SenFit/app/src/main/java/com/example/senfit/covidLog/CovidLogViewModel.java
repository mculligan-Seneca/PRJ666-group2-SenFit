/*
PRJ666 Sen-Fit
init date: Febuary 2nd 2021
Author Mitchell Culligan
Version 1.0
com.example.senfit.CovidLog ViewModel
This viewmodel class stores data for the current covid Log use case and interacts with the dataclient
 */
package com.example.senfit.covidLog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.CovidLog;

import java.util.ArrayList;
import java.util.List;

public class CovidLogViewModel extends ViewModel {
    private static final int WEEK_PERIOD=2;//number of weeks that a log is valid for
    private MutableLiveData<Integer> memberIdData;
    private LiveData<List<CovidLog>> recentMemberLogs;
    private DatabaseClient dbClient;
    private List<CovidSurveyQuestion> questionList;//stores questions and user responses
    public CovidLogViewModel(){

        dbClient = DatabaseClient.getInstance();

        this.questionList = new ArrayList<>();
        this.memberIdData= new MutableLiveData<>();
        this.recentMemberLogs= Transformations.switchMap(this.memberIdData,(id)-> dbClient
                .getAppDatabase()
                .getCovidLogDAO()
                .getLogsForMember(id,DateHelper.getDateMinusWeeks(WEEK_PERIOD)));
    }


    public void setMemberIdData(int id){
        this.memberIdData.setValue(id);
    }

    public LiveData<List<CovidLog>> getRecentMemberLogs(){
        return this.recentMemberLogs;
    }

    public void setQuestionList(String[] list){

        if(this.questionList.isEmpty()){
            for(int i=0;i<list.length;i++)
                this.questionList.add(new CovidSurveyQuestion(list[i],false));
        }
    }
    public List<CovidSurveyQuestion> getSurveyQuestions(){
        return this.questionList;
    }


    public boolean insertLog(){ //returns the status of the log
        //creates a log object and inserts it into the database
        boolean status=false;
        for(int i=0; i<this.questionList.size()&& !status;i++){
            if(!this.questionList.get(i).getResult())
                status=true;
        }
        boolean finalStatus = status;
        DatabaseClient.dbExecutors.execute(()->{
            CovidLog covidLog = new CovidLog(finalStatus,DateHelper.getCurrentDate(),memberIdData.getValue());
            dbClient.getAppDatabase()
                    .getCovidLogDAO()
                    .insertCovidLog(covidLog);
            //TODO:Communicate with backend
        });

        return status;


    }


}
