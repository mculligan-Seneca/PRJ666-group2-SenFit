/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
EnrollTrainingPlanActivity
This activity is the base activity for the member to enroll into a training plan use case
 */
package com.example.senfit.trainingPlan.enrollTrainingPlan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.senfit.R;
import com.example.senfit.navigator.NavigateFragment;
import com.example.senfit.navigator.Navigator;
import com.example.senfit.trainingPlan.TrainingPlanViewModelFactory;
import com.example.senfit.trainingPlan.enrollTrainingPlan.EnrollTrainingPlanViewModel;
import com.example.senfit.trainingPlan.enrollTrainingPlan.selectTrainingPortfolio.SelectTrainingPortfolioFragment;

public class EnrollTrainingPlanActivity extends AppCompatActivity implements Navigator, NavigateFragment {
    public static final String MEMBER_TAG="member_tag";
    private FragmentManager fm;
    private EnrollTrainingPlanViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll_training_plan);

        if(savedInstanceState==null) {
            int memberId=getIntent().getIntExtra(MEMBER_TAG,-1);
            this.viewModel = new ViewModelProvider(this,new TrainingPlanViewModelFactory(memberId)).get(EnrollTrainingPlanViewModel.class);
        }else{
            this.viewModel = new ViewModelProvider(this).get(EnrollTrainingPlanViewModel.class);
        }
        this.fm=getSupportFragmentManager();
        this.fm.beginTransaction()
                .add(R.id.training_plan_enroll_fragment,new SelectTrainingPortfolioFragment())
                .addToBackStack(null)
                .commit();
        getActionBar().setTitle(R.string.select_training_portfolio);
    }


    @Override
    public void navigateTo(Intent intent) {//starts new activity and finishes previous one
        startActivity(intent);
        finish();

    }

    @Override
    public void swapFragment(Fragment fragment, int titleId) {
        fm.beginTransaction()
                .replace(R.id.training_plan_enroll_fragment,fragment)
                .addToBackStack(null).commit();
        getActionBar().setTitle(titleId);

    }

}