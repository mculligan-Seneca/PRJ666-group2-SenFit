/*
PRJ666 Sen-Fit
init date: Feb 21st 2021
Author Mitchell Culligan
Version 1.0
Portfolio Adapter
This adapter class is used by the ui to display all the portfolios for the uset
 */
package com.example.senfit.fitnessPortfolio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.FitnessPortfolio;
import com.example.senfit.databinding.PortfolioItemBinding;

public class PortfolioAdapter extends RecyclerView.Adapter<PortfolioAdapter.PortfolioHolder> {
    private List<FitnessPortfolio> portfolioList;
    private Context context;


    public interface OnResultClickListener{
         void resultsSelected(FitnessPortfolio portfolio);
    }

    private OnResultClickListener listener;//callback interface
    public PortfolioAdapter(Context context, List<FitnessPortfolio> portfolios, OnResultClickListener l){
        this.context=context;
        this.portfolioList=portfolios;
        this.listener=l;
    }
    @NonNull
    @Override
    public PortfolioAdapter.PortfolioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       LayoutInflater inflator = LayoutInflater.from(this.context);
        PortfolioItemBinding binding =
                DataBindingUtil.inflate(inflator,R.layout.portfolio_item,parent,false);
        return new PortfolioHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PortfolioHolder holder, int position) {
        FitnessPortfolio portfolio = this.portfolioList.get(position);
        holder.bind(portfolio,listener);


    }

    @Override
    public int getItemCount() {
        return this.portfolioList.size();
    }

   static  class PortfolioHolder extends RecyclerView.ViewHolder {

        private PortfolioItemBinding binding;

       public PortfolioHolder(@NonNull PortfolioItemBinding itemBinding) {

           super(itemBinding.getRoot());
           this.binding=itemBinding;
       }
       public void bind(FitnessPortfolio portfolio,OnResultClickListener listener){
           this.binding.setPortfolio(portfolio);
           this.binding.setListener(listener);
           this.binding.executePendingBindings();//execute binding immeadiatley
       }
   }
}
