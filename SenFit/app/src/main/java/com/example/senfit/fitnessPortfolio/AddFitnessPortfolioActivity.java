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
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.senfit.R;
import com.example.senfit.uiHelpers.DialogBoxHelper;

public class AddFitnessPortfolioActivity extends AppCompatActivity implements View.OnClickListener{

    private AddFitnessPortfolioViewModel portfolioViewModel;
    private EditText heightIn,weigthIn, healthConcerns;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fitness_portfolio);
        this.portfolioViewModel = new ViewModelProvider(this).get(AddFitnessPortfolioViewModel.class);

        this.heightIn=findViewById(R.id.height_input);
        this.weigthIn=findViewById(R.id.weight_input);
        this.startBtn=findViewById(R.id.start_buttonId);
        this.healthConcerns=findViewById(R.id.health_concerns_input);
        this.startBtn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String heightStr=null, weightStr=null;
        heightStr=this.heightIn.getText().toString();
        weightStr=this.weigthIn.getText().toString();
        if(heightStr!=null &&!heightStr.isEmpty() && TextUtils.isDigitsOnly(heightStr)){
            if(weightStr!=null &&!weightStr.isEmpty() && TextUtils.isDigitsOnly(weightStr)){
                this.portfolioViewModel.getPortfolio().setHeight(Integer.parseInt(heightStr));
                this.portfolioViewModel.getPortfolio().setWeight(Integer.parseInt(weightStr));
                this.portfolioViewModel.getPortfolio().setHealthConcerns(this.healthConcerns.getText().toString());


            }
            else
            {
                DialogBoxHelper.createPositiveDialog(this,R.string.fitness_portfolio,
                        R.string.weight_errMssg,null)
                        .show();
            }
        }
        else{
            DialogBoxHelper.createPositiveDialog(this,R.string.fitness_portfolio,R.string.height_errMssg,null)
                    .show();
        }
    }

}