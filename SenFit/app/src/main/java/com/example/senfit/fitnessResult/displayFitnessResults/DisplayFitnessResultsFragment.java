/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
DisplayFitnessResultsFragment
This fragment is tasked with display the results associated with a portfolio. This fragment will be used
 */
package com.example.senfit.fitnessResult.displayFitnessResults;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;


public class DisplayFitnessResultsFragment extends Fragment {


    private static final String RESULTS_TAG="results_tag";
    private int portfolioId;
    public DisplayFitnessResultsFragment() {
        // Required empty public constructor
    }



    public static DisplayFitnessResultsFragment newInstance(int portfolioId){
        Bundle args = new Bundle();
        DisplayFitnessResultsFragment fragment = new DisplayFitnessResultsFragment();
        args.putInt(RESULTS_TAG,portfolioId);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.portfolioId=args.getInt(RESULTS_TAG,-1);//retrieve portfolio tag
        }
        else
        {
            this.portfolioId=-1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_display_fitness_results, container, false);
    }
}