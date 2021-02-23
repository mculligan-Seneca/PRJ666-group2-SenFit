/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessResultsActivity
This activity is the base controller for the add fitness result use case
 */
package com.example.senfit.fitnessResult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.senfit.R;
import android.content.Intent;
public class AddFitnessResultsActivity extends AppCompatActivity {

    public static final String ADD_RESULT_TAG="add_result_tag";


    private AddFitnessResultViewModel fitnessResultViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fitness_results);

        if(savedInstanceState==null) {//if viewmodel already instantiated then retrieve viewmodel else create new
            Intent args = getIntent();
            int portId = args.getIntExtra(ADD_RESULT_TAG, -1);
            this.fitnessResultViewModel = new ViewModelProvider(this,
                    new AddFitnessResultViewModelFactory(portId))
                    .get(AddFitnessResultViewModel.class);
        }else{
            this.fitnessResultViewModel = new ViewModelProvider(this).get(AddFitnessResultViewModel.class);
        }

    }
}