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
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;


public class DisplayFitnessResultsFragment extends Fragment {


    private static final String RESULTS_TAG="results_tag";
    private int portfolioId;
    private RecyclerView recyclerView;
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
        View v = inflater.inflate(R.layout.fragment_display_fitness_results, container, false);
        this.recyclerView=v.findViewById(R.id.fitness_results_recyclerView);
        return v; //TODO create adapter, create adapter view,create viewmodel & factory, create database view
        //TODO add fragment to backstack
    }
}