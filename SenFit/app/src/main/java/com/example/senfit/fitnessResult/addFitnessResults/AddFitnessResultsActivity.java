/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessResultsActivity
This activity is the base controller for the add fitness result use case
 */
package com.example.senfit.fitnessResult.addFitnessResults;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.Exercise;
import com.example.senfit.dataContext.entities.FitnessResult;
import com.example.senfit.exerciseWithReps.ExerciseWithReps;
import com.example.senfit.exerciseWithReps.ExerciseWithRepsFragment;
import com.example.senfit.ui.inperson.SenFitActivity;
import com.example.senfit.uiHelpers.DialogBoxHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddFitnessResultsActivity extends AppCompatActivity {

    public static final String ADD_RESULT_TAG="add_result_tag";
    private static final int REP_NUM=5;

    private AddFitnessResultViewModel fitnessResultViewModel;
    private FragmentManager fm;
    private ImageButton nextButton;
    private EditText heartBeatText;
    private List<ExerciseWithReps> exerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fitness_results);

        if(savedInstanceState==null) {//if viewmodel already instantiated then retrieve viewmodel else create new
            Intent args = getIntent();
            int portId = args.getIntExtra(ADD_RESULT_TAG, -1);
            this.fitnessResultViewModel = new ViewModelProvider(this,
                    new AddFitnessResultViewModelFactory(portId))
                    .get(AddFitnessResultViewModel.class);
        }else{
            this.fitnessResultViewModel = new ViewModelProvider(this).get(AddFitnessResultViewModel.class);
        }

        this.fm = getSupportFragmentManager();
        this.exerciseList = new ArrayList<>(0);
        this.heartBeatText = findViewById(R.id.heart_beatspm);
        this.nextButton = findViewById(R.id.next_button);
        this.nextButton.setEnabled(false);//in case the user attempts to submit before data loads
        this.nextButton.setOnClickListener(v->{
            String heartstr = heartBeatText.getText().toString();
            if(heartstr.trim().length()==0 || !TextUtils.isDigitsOnly(heartstr) || heartstr.trim().equals("0")){
                Toast.makeText(this,R.string.heart_beat_errMssg,Toast.LENGTH_LONG);
            }else{
                //valid input
                ExerciseWithReps e = exerciseList.get(fitnessResultViewModel.getIndex());
                int portfolioId = fitnessResultViewModel.getPortfolioId();
                FitnessResult result = new FitnessResult(portfolioId
                        ,e.exercise.getExerciseId(),
                        e.reps,Integer.parseInt(heartstr));
                fitnessResultViewModel.addResult(result);
                if(fitnessResultViewModel.hasNext()){
                    heartBeatText.getText().clear();
                    setExerciseFragment();//prepare next exercise

                }else{// else if finished insert results into database and return to home activity
                    fitnessResultViewModel.insert();//
                    Toast.makeText(this,R.string.results_insert_msg,Toast.LENGTH_LONG);
                    Intent intent = new Intent(this, SenFitActivity.class);
                    startActivity(intent);
                    finish();//start main fiish result
                }
            }
        });
        this.fitnessResultViewModel.getExerciseList().observe(this,list->{

            nextButton.setEnabled(true);//enable button
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                exerciseList = list.stream()
                            .map(e->{
                                ExerciseWithReps er = new ExerciseWithReps(e,REP_NUM);
                                return er;
                            })
                            .collect(Collectors.toList());//maps exercise to exercise with reps
            }else{
                exerciseList.clear();
                for(Exercise e:list){
                    exerciseList.add(new ExerciseWithReps(e,REP_NUM));
                }
            }
            setExerciseFragment();

        });

    }
    public void setExerciseFragment(){

        Fragment fragment = this.fm.findFragmentById(R.id.exercise_with_RepLayout);
        FragmentTransaction transaction=this.fm.beginTransaction();
        if(fragment==null){

            //TODO:set fragment to first exercise
            fragment = new ExerciseWithRepsFragment(this.exerciseList.get(this.fitnessResultViewModel.getIndex()));
           transaction.add(R.id.exercise_with_RepLayout,fragment);//adds exercise with rep fragment
        }
        else {
            //set fragment to next exercise and replace fragment
            fragment = new ExerciseWithRepsFragment(this.exerciseList.get(this.fitnessResultViewModel.getIndex()));
            transaction.replace(R.id.exercise_with_RepLayout,fragment);
            }
    }
}