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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.FitnessPortfolio;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FitnessPortfolioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FitnessPortfolioFragment extends Fragment implements PortfolioAdapter.OnResultClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MEMBER_ARG = "member_arg";


    // TODO: Rename and change types of parameters
    private int memberId;
    private FitnessPortfolioViewModel portfolioViewModel;
    private RecyclerView recyclerView;
    private List<FitnessPortfolio> portfolioList;
    private PortfolioAdapter adapter;
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
            this.memberId= 0;//always set arguements
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_fitness_portfolio, container, false);
        this.portfolioViewModel = new ViewModelProvider(getActivity(),new PortfolioViewModelFactory(memberId))
                .get(FitnessPortfolioViewModel.class);
        this.portfolioList = new ArrayList<>(0);
        this.recyclerView = v.findViewById(R.id.portfolio_listId);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapter= new PortfolioAdapter(getContext(),this.portfolioList,this);
        this.recyclerView.setAdapter(this.adapter);
        //update list when data set has changed
        this.portfolioViewModel.getPortfolioData().observe(getViewLifecycleOwner(),(data)->{
                updatePortfolioList(data);
        });
        return v;
    }

    public void updatePortfolioList(List<FitnessPortfolio> list){
        this.portfolioList.clear();
        this.portfolioList.addAll(list);
        this.adapter.notifyDataSetChanged();
    }
    @Override
    public void resultsSelected(FitnessPortfolio portfolio) {

    }
}