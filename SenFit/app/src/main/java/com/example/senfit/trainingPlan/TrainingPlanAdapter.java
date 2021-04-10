/*
PRJ666 Sen-Fit
init date: April 10th 2021
Author Mitchell Culligan
Version 1.0
TrainingPlanAdapter
This adapter class displays a training plan item in a recycler view
 */
package com.example.senfit.trainingPlan;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.example.senfit.dataContext.entities.TrainingPlan;
import com.example.senfit.dataContext.views.TrainingPlanView;

public class TrainingPlanAdapter extends RecyclerView.Adapter<TrainingPlanAdapter.TrainingPlanViewHolder> {
    private List<TrainingPlanView> planList;
    private Context context;
    private SelectPlanListener listener;

    private interface SelectPlanListener{
        public void selectPlan();
    }


    public TrainingPlanAdapter(@NonNull Context context, List<TrainingPlanView> plans, SelectPlanListener listsner) {
        this.context=context;
        this.planList=plans;
        this.listener=listener;
    }
    @NonNull
    @Override
    public TrainingPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator= LayoutInflater.from(this.context);
        View v;
        return new TrainingPlanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingPlanViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return this.planList.size();
    }


    public class TrainingPlanViewHolder extends RecyclerView.ViewHolder {
        public TrainingPlanViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


}
