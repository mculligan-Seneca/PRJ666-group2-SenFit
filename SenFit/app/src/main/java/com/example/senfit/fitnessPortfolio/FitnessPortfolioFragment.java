/*
PRJ666 Sen-Fit
init date: Feb 18th 2021
Author Mitchell Culligan
Version 1.0
Fitness Portfolio fragment
This fragment class repersents the view of the fitness portfolio.
 */
package com.example.senfit.fitnessPortfolio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;
import com.example.senfit.login.LoginHelper;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FitnessPortfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FitnessPortfolioFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MEMBER_ARG = "member_arg";


    // TODO: Rename and change types of parameters
    private int memberId;

    public FitnessPortfolioFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FitnessPortfolioFragment newInstance(int memberId) {
        FitnessPortfolioFragment fragment = new FitnessPortfolioFragment();
        Bundle args = new Bundle();
        args.putInt(MEMBER_ARG, memberId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.memberId = getArguments().getInt(MEMBER_ARG);

        }
        else{
            this.memberId= LoginHelper.MEMBER_ID;//fix this
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fitness_portfolio, container, false);
    }
}