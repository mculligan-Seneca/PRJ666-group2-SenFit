/*
PRJ666 Sen-Fit
init date: April 10th 2021
Author Mitchell Culligan
Version 1.0
ListTrainingPlanFragment
This fragment is the view for a list of training plans for a member
 */
package com.example.senfit.trainingPlan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListTrainingPlanFragment extends Fragment {


    private RecyclerView recyclerView;
    private FloatingActionButton addButton;

    public ListTrainingPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.list_fragment_training_plan, container, false);
        this.addButton=v.findViewById(R.id.floating_add__plan);
        this.recyclerView=v.findViewById(R.id.training_plan_list);
        return v;
    }
}