/*
PRJ666 Sen-Fit
init date: Feb 22nd 2021
Author Mitchell Culligan
Version 1.0
FitnessMeasureFragment
This fragment class is designed with the intent for the user to enter in their measurements such as height and weight
 */
package com.example.senfit.fitnessPortfolio;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.room.util.StringUtil;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.senfit.R;
import com.example.senfit.uiHelpers.DialogBoxHelper;

import java.text.NumberFormat;


public class FitnessMeasureFragment extends Fragment implements View.OnClickListener{

    private static final String MEASURE_TAG="measure_tag";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



    // TODO: Rename and change types of parameters

    private EditText heightIn,weigthIn, healthConcerns;
    private Button startBtn;
    private AddFitnessPortfolioViewModel portfolioViewModel;
    public FitnessMeasureFragment() {
        // Required empty public constructor
    }

    public interface OnNextListener{
        public void setNext();
    }

    public OnNextListener listener;

    // TODO: Rename and change types and number of parameters


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof OnNextListener){
            this.listener= (OnNextListener)context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.listener=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=  inflater.inflate(R.layout.fragment_fitness_measure, container, false);
        this.heightIn=v.findViewById(R.id.height_input);
        this.weigthIn=v.findViewById(R.id.weight_input);
        this.startBtn=v.findViewById(R.id.start_buttonId);
        this.healthConcerns= v.findViewById(R.id.health_concerns_input);
        this.startBtn.setOnClickListener(this);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.portfolioViewModel = new ViewModelProvider(getActivity()).get(AddFitnessPortfolioViewModel.class);
        //viewmodel store owner is the activity that calls the fragment

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
                //TODO:Call back to main activity
                if(this.listener!=null)
                    this.listener.setNext();
            }
            else
            {
                DialogBoxHelper.createPositiveDialog(getContext(),R.string.fitness_portfolio,
                        R.string.weight_errMssg,null)
                        .show();
            }
        }
        else{
            DialogBoxHelper.createPositiveDialog(getContext(),R.string.fitness_portfolio,R.string.height_errMssg,null)
            .show();
        }
    }
}