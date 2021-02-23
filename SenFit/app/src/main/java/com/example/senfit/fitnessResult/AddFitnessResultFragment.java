/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessResultFragment
This fragment class provides user with exercise to perform and then saves their heart beats in a viewmodel
 */
package com.example.senfit.fitnessResult;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.senfit.R;


public class AddFitnessResultFragment extends Fragment {

    private static final String HEART_BEAT_TAG="heart_beats";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private EditText heartBeatText;
    private TextView exerciseText;
    private ImageButton nextButton;
    private AddFitnessResultViewModel fitnessResultViewModel;
    private int heartBeatPM;//for storing text as integer

    public AddFitnessResultFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState== null) {
           this.heartBeatPM=savedInstanceState.getInt(HEART_BEAT_TAG,-1);
        }else{
            this.heartBeatPM=0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_fitness_result, container, false);
        this.exerciseText = v.findViewById(R.id.fitness_exerciseId);
        this.heartBeatText = v.findViewById(R.id.heart_beatspm);
        this.nextButton = v.findViewById(R.id.next_button);

        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fitnessResultViewModel = new ViewModelProvider(getActivity()).get(AddFitnessResultViewModel.class);
        this.fitnessResultViewModel.getExerciseLiveData().observe(getViewLifecycleOwner(),exercise->{
           exerciseText.setText(String.format("Perform %d %s",0,exercise.getExerciseName()));
        });
        
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(HEART_BEAT_TAG,this.heartBeatPM);
    }
}