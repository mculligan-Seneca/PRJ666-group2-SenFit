/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
Select Trainer
This fragment provides a list of trainers for the user to select
 */
package com.example.senfit.trainingPlan.enrollTrainingPlan.selectTrainer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.Trainer;
import com.example.senfit.navigator.Navigator;
import com.example.senfit.trainingPlan.enrollTrainingPlan.EnrollTrainingPlanViewModel;
import com.example.senfit.ui.inperson.SenFitActivity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class SelectTrainerFragment extends Fragment implements TrainerAdapter.OnSelectTrainer {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Trainer> trainerList;
    private EnrollTrainingPlanViewModel viewModel;
    private RecyclerView recyclerView;
    private TrainerAdapter adapter;
    private Navigator navigator;
    public SelectTrainerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Navigator)
            this.navigator=(Navigator)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.navigator=null;
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
        View v= inflater.inflate(R.layout.fragment_select_trainer, container, false);
        this.trainerList= new ArrayList<>(0);
        this.recyclerView=v.findViewById(R.id.select_training_list);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter= new TrainerAdapter(getContext(),this.trainerList,this);
        this.recyclerView.setAdapter(this.adapter);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.viewModel= new ViewModelProvider(getActivity()).get(EnrollTrainingPlanViewModel.class);
        this.viewModel.getTrainerData().observe(getViewLifecycleOwner(),list->{
            trainerList.clear();
            trainerList.addAll(list);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void selectTrainer(Trainer trainer) {
        this.viewModel.setTrainer(trainer.getTrainerId());
        this.viewModel.submitPlan()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    private Disposable disposable;
                    @Override
                    public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {
                            disposable=d;
                    }

                    @Override
                    public void onComplete() {

                        Toast.makeText(getContext(),R.string.training_plan_submitted,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), SenFitActivity.class);//finsih activity and go back to main activity
                        navigator.navigateTo(intent);
                        if(!disposable.isDisposed())
                            disposable.dispose();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.e("submit_training_plan",e.getMessage());
                        Toast.makeText(getContext(),R.string.training_plan_submit_err,Toast.LENGTH_LONG).show();
                        if(!disposable.isDisposed())
                            disposable.dispose();
                    }
                });

    }
}