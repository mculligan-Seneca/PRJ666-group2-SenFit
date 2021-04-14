/*
PRJ666 Sen-Fit
init date: April 10th 2021
Author Mitchell Culligan
Version 1.0
TrainingPlanAdapter
This adapter class displays a training plan item in a recycler view
 */
package com.example.senfit.trainingPlan.listTrainingPlan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import com.example.senfit.R;
import com.example.senfit.dataContext.views.TrainingPlanView;
import com.example.senfit.uiHelpers.DateTimeFormatHelper;

public class TrainingPlanAdapter extends RecyclerView.Adapter<TrainingPlanAdapter.TrainingPlanViewHolder> {
    private List<TrainingPlanView> planList;
    private Context context;
    private SelectPlanListener listener;

    public interface SelectPlanListener{
        public void selectPlan(int trainingPlanId,String planName);
    }


    public TrainingPlanAdapter(@NonNull Context context, List<TrainingPlanView> plans, SelectPlanListener listsner) {
        this.context=context;
        this.planList=plans;
        this.listener=listener;
    }
    @NonNull
    @Override
    public TrainingPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(this.context);
        View v= inflater.inflate(R.layout.training_plan_item,parent,false);
        return new TrainingPlanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainingPlanViewHolder holder, int position) {
        TrainingPlanView plan=this.planList.get(position);
        holder.planName.setText(plan.planName);
        holder.instructorName.setText(plan.instrucotrName);
        holder.startDate.setText(DateTimeFormatHelper.formatDate(plan.startDate));
        holder.trainerEmail.setText(plan.trainerEmail);


    }



    @Override
    public int getItemCount() {
        return this.planList.size();
    }


    public class TrainingPlanViewHolder extends RecyclerView.ViewHolder {

        public final TextView planName;
        public final TextView startDate;
        public final TextView instructorName;
        public final TextView trainerEmail;
        public TrainingPlanViewHolder(@NonNull View itemView) {

            super(itemView);
            this.instructorName=itemView.findViewById(R.id.plan_instructor_name);
            this.planName=itemView.findViewById(R.id.plan_title);
            this.startDate=itemView.findViewById(R.id.plan_startDate);
            this.trainerEmail=itemView.findViewById(R.id.plan_trainer_email);
        }

    }


}
