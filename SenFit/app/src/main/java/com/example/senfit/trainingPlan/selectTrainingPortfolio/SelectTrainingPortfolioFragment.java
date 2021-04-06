/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
SelectTrainingPortfolioFragment
This fragment class repersents a list of fitness portfolios for the member to select for a trainingplan.
 */
package com.example.senfit.trainingPlan.selectTrainingPortfolio;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.fitnessPortfolio.PortfolioAdapter;
import com.example.senfit.fitnessPortfolio.addFitnessPortfolio.AddFitnessPortfolioActivity;
import com.example.senfit.navigator.NavigateFragment;
import com.example.senfit.navigator.Navigator;
import com.example.senfit.trainingPlan.EnrollTrainingPlanViewModel;
import com.example.senfit.trainingPlan.selectGymTraining.SelectTrainingGymFragment;

import java.util.ArrayList;
import java.util.List;


public class SelectTrainingPortfolioFragment extends Fragment implements PortfolioAdapter.OnResultClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters

    private EnrollTrainingPlanViewModel viewModel;
    private RecyclerView recyclerView;
    private PortfolioAdapter adapter;
    private ArrayList<FitnessPortfolio> portfolioList;
    private NavigateFragment navigateFragment;
    private Navigator navigator;
    private Button addPortfolioButton;

    public SelectTrainingPortfolioFragment() {
        // Required empty public constructor
        this.navigateFragment=null;
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
        if(context instanceof Navigator)
            this.navigator=(Navigator)context;
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
        View v=inflater.inflate(R.layout.fragment_select_training_portfolio, container, false);
        this.portfolioList = new ArrayList<>(0);
        this.recyclerView=v.findViewById(R.id.training_portfolio_list);
        this.addPortfolioButton=v.findViewById(R.id.create_portfolio_button);
        this.addPortfolioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddFitnessPortfolioActivity.class);
                intent.putExtra(AddFitnessPortfolioActivity.MEMBER_TAG,viewModel.getMemberId());
                navigator.navigateTo(intent);
            }
        });
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter = new PortfolioAdapter(getContext(),this.portfolioList,this);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.viewModel = new ViewModelProvider(getActivity()).get(EnrollTrainingPlanViewModel.class);
        this.viewModel.getLivePortfolioData().observe(getViewLifecycleOwner(), this::updateList);
    }

    public void updateList(List<FitnessPortfolio> list){
        this.portfolioList.clear();
        this.portfolioList.addAll(list);
        this.adapter.notifyDataSetChanged();
        if(!this.portfolioList.isEmpty()) {
            this.addPortfolioButton.setVisibility(View.GONE);
            this.addPortfolioButton.setClickable(false);
        }
        else {
            this.addPortfolioButton.setVisibility(View.VISIBLE);
            this.addPortfolioButton.setClickable(true);
        }
    }
    @Override
    public void resultsSelected(FitnessPortfolio portfolio) {
        this.viewModel.setFitnessPortfolioId(portfolio.getFitnessPortfolioId());
        this.navigateFragment.swapFragment(new SelectTrainingGymFragment(),R.string.select_training_gym);
    }


}