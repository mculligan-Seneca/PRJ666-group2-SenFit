/*
PRJ666 Sen-Fit
init date: Feb 22nd 2021
Author Mitchell Culligan
Version 1.0
Add fitness portfolio activity
This activity allows the user to add a fitness portfolio to their account
 */
package com.example.senfit.fitnessPortfolio.addFitnessPortfolio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.senfit.R;
import com.example.senfit.fitnessResult.AddFitnessResultsActivity;
import com.example.senfit.login.LoginHelper;
import com.example.senfit.uiHelpers.DialogBoxHelper;

public class AddFitnessPortfolioActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String MEMBER_TAG="member__tag";
    private AddFitnessPortfolioViewModel portfolioViewModel;
    private EditText heightIn,weigthIn, healthConcerns;
    private Button startBtn;
    private int memberId;
    private long portfolioId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fitness_portfolio);
        if(savedInstanceState==null) {
            Intent args = getIntent();
            this.memberId=args.getIntExtra(MEMBER_TAG,-1);

        }else{
            this.memberId=savedInstanceState.getInt(MEMBER_TAG,-1);
        }

        this.portfolioViewModel = new ViewModelProvider(this).get(AddFitnessPortfolioViewModel.class);

        this.heightIn=findViewById(R.id.height_input);
        this.weigthIn=findViewById(R.id.weight_input);
        this.startBtn=findViewById(R.id.start_buttonId);
        this.healthConcerns=findViewById(R.id.health_concerns_input);
        this.startBtn.setOnClickListener(this);

        this.portfolioViewModel.getRowNumData().observe(this,(row)->{// observe weather portfolio has been inserted 
            if(row!=null){
                portfolioId=row;
                Intent args = new Intent(this, AddFitnessResultsActivity.class);
                args.putExtra(AddFitnessResultsActivity.ADD_RESULT_TAG,portfolioId);
                startActivity(args);
                finish();
                //TODO:Start exercise result activity
            }

        });

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
                this.portfolioViewModel.getPortfolio().setMemberId(this.memberId);
                this.portfolioViewModel.insertPortfolio();//insert portfolio into database


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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MEMBER_TAG,this.memberId);
    }
}