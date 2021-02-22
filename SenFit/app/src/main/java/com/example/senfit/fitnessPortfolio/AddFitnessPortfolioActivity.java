/*
PRJ666 Sen-Fit
init date: Feb 22nd 2021
Author Mitchell Culligan
Version 1.0
Add fitness portfolio activity
This activity allows the user to add a fitness portfolio to their account
 */
package com.example.senfit.fitnessPortfolio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import com.example.senfit.R;
public class AddFitnessPortfolioActivity extends AppCompatActivity implements FitnessMeasureFragment.OnNextListener{

    private AddFitnessPortfolioViewModel portfolioViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fitness_portfolio);
        this.portfolioViewModel = new ViewModelProvider(this).get(AddFitnessPortfolioViewModel.class);




    }

    @Override
    public void setNext() {

    }
}