/*
PRJ666 Sen-Fit
init date: Febuary 10th 2021
Author Mitchell Culligan
Version 1.0
Survey Adapter class
This class is the adapter to hold a dynamic list of survey questions that will be displayed to the
view via a recycler adapter.
 */
package com.example.senfit.covidLog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;


import java.util.List;

public class SurveyAdapter extends RecyclerView.Adapter<SurveyAdapter.SurveyHolder> {

    private List<CovidSurveyQuestion> questionList;
    private Context context;
    public SurveyAdapter(Context context,List<CovidSurveyQuestion> questions){
        this.context=context;
        this.questionList=questions;
    }
    @NonNull
    @Override
    public SurveyAdapter.SurveyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(this.context);
        View v = inflator.inflate(R.layout.covid_symptom_item,parent,false);


            //inflate layout then get binding for data
        return new SurveyHolder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyHolder holder, int position) {
            CovidSurveyQuestion question = questionList.get(position);
            holder.getQuestion().setText(question.getQuestion());


    }



    @Override
    public int getItemCount() {
        return this.questionList.size();
    }

    public class SurveyHolder extends RecyclerView.ViewHolder{

        private TextView question;
        private CheckBox checkBox;

        public SurveyHolder(View v) {
            super(v);
            question = v.findViewById(R.id.question_id);
            checkBox=v.findViewById(R.id.checkbox_id);
            checkBox.setOnClickListener((view)->{
                CovidSurveyQuestion q= questionList.get(getAdapterPosition());
                q.setResult(!q.getResult());
            });
        }


        public CheckBox getCheckBox() {
            return checkBox;
        }

        public TextView getQuestion() {
            return question;
        }
    }
}
