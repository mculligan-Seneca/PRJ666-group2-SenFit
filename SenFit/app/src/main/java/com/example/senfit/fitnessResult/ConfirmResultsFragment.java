/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
ConfirmResultsFragment
This fragment displays the results the user has just completed once the user clicks the confirm button they
will be taken back to the main activity
 */
package com.example.senfit.fitnessResult;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;


public class ConfirmResultsFragment extends Fragment {

    public static final String CONFIRM_RESULT_TAG="confirm_result_tag";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private int portfolioId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            portfolioId=args.getInt(CONFIRM_RESULT_TAG,-1);
        }
        else
            {
            portfolioId=-1;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_results, container, false);
    }
}