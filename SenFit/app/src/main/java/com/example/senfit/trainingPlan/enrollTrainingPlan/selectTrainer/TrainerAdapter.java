/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
TrainerAdapter
This adapter class is responsible for displaying a list of trainer objects in a recycler view.
 */
package com.example.senfit.trainingPlan.enrollTrainingPlan.selectTrainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.dataContext.entities.Trainer;
import com.example.senfit.R;

import java.util.List;

public class TrainerAdapter extends RecyclerView.Adapter<TrainerAdapter.TrainerViewHolder> {
    private Context context;
    private List<Trainer> trainerList;
    private OnSelectTrainer listener;

    public interface OnSelectTrainer{
        public void selectTrainer(Trainer trainer);
    }


    public TrainerAdapter(Context context,List<Trainer> trainerList, OnSelectTrainer listener){
        this.context=context;
        this.trainerList=trainerList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public TrainerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View v = inflater.inflate(R.layout.trainer_list_item,parent,false);
        return new TrainerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TrainerViewHolder holder, int position) {
        Trainer trainer= this.trainerList.get(position);
        holder.full_name.setText(trainer.getFirstName()+" "+trainer.getLastName());
        holder.email.setText(trainer.getEmail());
        holder.selectTrainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.selectTrainer(trainer);
            }
        });
    }



    @Override
    public int getItemCount() {
        return this.trainerList.size();
    }

    public class TrainerViewHolder extends RecyclerView.ViewHolder {
        public final TextView full_name;
        public final TextView email;
        public final ImageButton selectTrainer;
        public TrainerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.full_name=itemView.findViewById(R.id.trainer_full_name);
            this.email=itemView.findViewById(R.id.trainer_email);
            this.selectTrainer=itemView.findViewById(R.id.select_trainer_button);

        }
    }
}
