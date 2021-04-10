/*
PRJ666 Sen-Fit
init date: April 6th 2021
Author Mitchell Culligan
Version 1.0
GymLocationAdapter
This adapter class is designed with the purpose to display Sen-fit gym locations for a recyclerview
 */
package com.example.senfit.trainingPlan.enrollTrainingPlan.selectGymTraining;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.GymLocation;

import java.util.List;

public class GymLocationAdapter extends RecyclerView.Adapter<GymLocationAdapter.GymLocationViewHolder> {
    private Context context;
    private List<GymLocation> gymList;
    private OnSelectLocation listener;
    public interface  OnSelectLocation{
        public void selectGym(GymLocation gym);
    }
    public GymLocationAdapter(Context context, List<GymLocation> list, OnSelectLocation selectLocation){
        this.context=context;
        this.gymList=list;
        this.listener=selectLocation;
    }
    @NonNull
    @Override
    public GymLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(this.context);
        View v = inflator.inflate(R.layout.gym_location_item,parent,false);
        return new GymLocationViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GymLocationViewHolder holder, int position) {
            GymLocation location = this.gymList.get(position);
            holder.postal_code.setText(location.getPostalCode());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.selectGym(location);
                }
            });
    }



    @Override
    public int getItemCount() {
        return this.gymList.size();
    }

    public class GymLocationViewHolder extends RecyclerView.ViewHolder {
        public final TextView postal_code;
        public GymLocationViewHolder(@NonNull View itemView) {
            super(itemView);
            this.postal_code=itemView.findViewById(R.id.training_gym_postal_code);
        }
    }
}
