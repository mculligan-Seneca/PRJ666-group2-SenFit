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
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.R;
import com.example.senfit.databinding.CovidSymptomItemBinding;

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
        CovidSymptomItemBinding binding = DataBindingUtil.inflate(inflator, R.layout.covid_symptom_item,parent,false);
            //inflate layout then get binding for data
        return new SurveyHolder(binding) ;
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyHolder holder, int position) {
            CovidSurveyQuestion question = questionList.get(position);
            holder.bind(question);
    }



    @Override
    public int getItemCount() {
        return this.questionList.size();
    }

    public class SurveyHolder extends RecyclerView.ViewHolder{

        private CovidSymptomItemBinding binding;// binder for data binding
        public SurveyHolder(CovidSymptomItemBinding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }

        public void bind(CovidSurveyQuestion question){
            this.binding.setCovidQuestion(question);//actually binds the question to the view
            this.binding.executePendingBindings();//updates the views to the bindings
        }
    }
}
