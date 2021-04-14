/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
SelectTrainingGymFragment
This fragment provides the member with a list of gym locations to select for their training plan
 */
package com.example.senfit.trainingPlan.enrollTrainingPlan.selectGymTraining;

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
import com.example.senfit.dataContext.entities.GymLocation;
import com.example.senfit.navigator.NavigateFragment;
import com.example.senfit.trainingPlan.enrollTrainingPlan.EnrollTrainingPlanViewModel;
import com.example.senfit.trainingPlan.enrollTrainingPlan.selectTrainer.SelectTrainerFragment;

import java.util.ArrayList;


public class SelectTrainingGymFragment extends Fragment implements GymLocationAdapter.OnSelectLocation {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView recyclerView;
    private GymLocationAdapter adapter;
    private EnrollTrainingPlanViewModel viewModel;
    private ArrayList<GymLocation> gymList;
    private NavigateFragment navigateFragment;

    public SelectTrainingGymFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
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
        View v=inflater.inflate(R.layout.fragment_select_training_gym, container, false);
        this.gymList= new ArrayList<>(0);
        this.recyclerView=v.findViewById(R.id.training_gym_location_list);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new GymLocationAdapter(getContext(),this.gymList,this);
        this.recyclerView.setAdapter(this.adapter);

        return v;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.viewModel = new ViewModelProvider(getActivity()).get(EnrollTrainingPlanViewModel.class);
        this.viewModel.getGymLocationData().observe(getViewLifecycleOwner(),list->{
            this.gymList.clear();
            this.gymList.addAll(list);
            this.adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void selectGym(GymLocation gym) {
        this.viewModel.setGymLocation(gym.getGymLocationId());

        this.navigateFragment.swapFragment(new SelectTrainerFragment(),R.string.select_trainer_plan);
    }
}