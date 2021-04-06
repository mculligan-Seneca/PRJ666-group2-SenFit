/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
EnrollTrainingPlanActivity
This activity is the base activity for the member to enroll into a training plan use case
 */
package com.example.senfit.trainingPlan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.senfit.R;

public class EnrollTrainingPlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_training_plan);
    }
}