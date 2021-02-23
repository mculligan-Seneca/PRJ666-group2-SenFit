/*
PRJ666 Sen-Fit
init date: Feb 22nd 2021
Author Mitchell Culligan
Version 1.0
ExerciseStartFragment
This fragment repersents the start page before the user starts performing exercises
 */
package com.example.senfit.fitnessResult;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;


public class ExerciseStartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    public ExerciseStartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_start, container, false);
    }

    public void startExercise(View v){
        NavController navController= Navigation.findNavController(v);
        navController.navigate(R.id.action_exercise_start_to_fitness_result);
       // navController.navigate
        //TODO:Navigate to next fragment LOOK AT SAFE ARGS
    }
}