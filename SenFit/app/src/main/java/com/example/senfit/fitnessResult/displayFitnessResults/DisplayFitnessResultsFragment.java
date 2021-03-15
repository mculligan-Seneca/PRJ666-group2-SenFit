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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;
import com.example.senfit.dataContext.views.FitnessResultView;
import java.util.ArrayList;
import java.util.List;
public class DisplayFitnessResultsFragment extends Fragment {


    private static final String RESULTS_TAG="results_tag";
    private int portfolioId;
    private RecyclerView recyclerView;
    private ResultAdapter adapter;
    private ArrayList<FitnessResultView> resultList;
    private DisplayFitnessResultsViewModel displayFitnessResultsViewModel;
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
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.resultList = new ArrayList<>(0);
        this.adapter= new ResultAdapter(getContext(),this.resultList);
        this.recyclerView.setAdapter(this.adapter);
        return v; //TODO create adapter, create adapter view,create viewmodel & factory, create database view
        //TODO add fragment to backstack
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.displayFitnessResultsViewModel = new ViewModelProvider(getActivity(),
                new DisplayFitnessResultsViewModelFactory(this.portfolioId)).get(DisplayFitnessResultsViewModel.class);
        this.displayFitnessResultsViewModel.getResultList().observe(getViewLifecycleOwner(),list->{
            updateList(list);
        });
    }

    public void updateList(List<FitnessResultView> data){
        this.resultList.clear();
        this.resultList.addAll(data);
        this.adapter.notifyDataSetChanged();
    }
}