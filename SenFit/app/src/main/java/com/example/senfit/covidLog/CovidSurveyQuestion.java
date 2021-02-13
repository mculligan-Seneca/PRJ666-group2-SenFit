/*
PRJ666 Sen-Fit
init date: Febuary 10th 2021
Author Mitchell Culligan
Version 1.0
Covid Survey Question  Class
This class repersents the member data of the covid log survey. It extends the base observable class
so the boolean result can have two way binding with the UI.
 */
package com.example.senfit.covidLog;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.senfit.BR;


public class CovidSurveyQuestion {

    private final String question;
    private boolean result;


    public CovidSurveyQuestion(String question, boolean result){
        this.question=question;
        this.result = result;
    }


    public boolean getResult(){
       return this.result;
    }

    public String getQuestion(){
        return this.question;
    }

    public void setResult(boolean result){
        this.result=result;


    }


    @Override
    public boolean equals(Object obj){//easy way to check if false exists in collection
        boolean isSame=false;

        if(obj instanceof CovidSurveyQuestion) {
            isSame = ((CovidSurveyQuestion)obj).result==this.result;
        } else if(obj instanceof Boolean) {
            isSame = (Boolean) obj ==this.result;
        }

        return isSame;


    }

}
