package com.example.senfit.fitnessResult.displayFitnessResults;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.FitnessResult;
import com.example.senfit.dataContext.views.FitnessResultView;
import java.util.List;

import javax.xml.transform.Result;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private Context context;
    private List<FitnessResultView> resultList;

    public ResultAdapter(Context context,List<FitnessResultView> resultList){
        this.context=context;
        this.resultList=resultList;
    }
    @NonNull
    @Override
    public ResultAdapter.ResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(this.context);
        View v = inflator.inflate(R.layout.fitness_result_item,parent,false);

        return new ResultViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultAdapter.ResultViewHolder holder, int position) {
        FitnessResultView result= resultList.get(position);
        holder.exerciseItem.setText(result.getExerciseName());
        holder.repItem.setText(String.valueOf(result.getRepsNum()));
        holder.heartBeatItem.setText(String.valueOf(result.getBeatsPM()));

    }

    @Override
    public int getItemCount() {
        return this.resultList.size();
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder {
        public final TextView exerciseItem;
        public final TextView repItem;
        public final TextView heartBeatItem;
        public ResultViewHolder(@NonNull View itemView) {
            super(itemView);
            this.exerciseItem = itemView.findViewById(R.id.exercise_item);
            this.repItem=itemView.findViewById(R.id.rep_item);
            this.heartBeatItem=itemView.findViewById(R.id.hbp_item);
        }
    }
}
