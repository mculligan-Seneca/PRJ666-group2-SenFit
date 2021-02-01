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
package com.example.senfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CovidSurveyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_survey);
    }
}