/*
PRJ666 Sen-Fit
init date: April 10th 2021
Author Mitchell Culligan
Version 1.0
ListTrainingPlanFragment
This fragment is the view for a list of training plans for a member
 */
package com.example.senfit.trainingPlan.listTrainingPlan;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;
import com.example.senfit.dataContext.views.TrainingPlanView;
import com.example.senfit.navigator.NavigateFragment;
import com.example.senfit.trainingPlan.TrainingPlanViewModelFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListTrainingPlanFragment extends Fragment implements TrainingPlanAdapter.SelectPlanListener {


    private RecyclerView recyclerView;
    private FloatingActionButton addButton;
    private TrainingPlanAdapter adapter;
    private ArrayList<TrainingPlanView> planList;
    private NavigateFragment navigateFragment;
    private ListTrainingPlanViewModel viewModel;
    private int memberId;

    public ListTrainingPlanFragment(int memberId) {
        // Required empty public constructor
        this.memberId=memberId;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof NavigateFragment)
            this.navigateFragment=(NavigateFragment)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.navigateFragment=null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.planList= new ArrayList<>(0);

        View v =inflater.inflate(R.layout.list_fragment_training_plan, container, false);
        this.addButton=v.findViewById(R.id.floating_add__plan);
        this.recyclerView=v.findViewById(R.id.training_plan_list);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter= new TrainingPlanAdapter(getContext(),this.planList,this);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.viewModel = new ViewModelProvider(getActivity(),new TrainingPlanViewModelFactory(this.memberId))
                .get(ListTrainingPlanViewModel.class);
        this.viewModel.getTrainingPlanLiveData().observe(getViewLifecycleOwner(),list->{
            this.planList.clear();
            this.planList.addAll(list);
            this.adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void selectPlan(int trainingPlanId) {
            //TODO Set up view for single training plan
    }
}