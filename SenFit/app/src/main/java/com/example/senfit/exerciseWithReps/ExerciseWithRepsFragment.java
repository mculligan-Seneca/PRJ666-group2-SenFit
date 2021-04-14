/*
PRJ666 Sen-Fit
init date: mar 12th 2023
Author Mitchell Culligan
Version 1.0
ExerciseWithRepsFragment
this fragment repersents the display of an exerciseWith reps object
 */
package com.example.senfit.exerciseWithReps;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.senfit.R;
import com.example.senfit.uiHelpers.DialogBoxHelper;


public class ExerciseWithRepsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    private TextView exerciseMessage;
    private ExerciseWithReps exercise;
    private Button getDescBtn;
    public ExerciseWithRepsFragment(ExerciseWithReps e) {
        // Required empty public constructor
        this.exercise=e;
    }

    public ExerciseWithRepsFragment() {
        // Required empty public constructor
        this.exercise=null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_exercise_with_reps, container, false);
        this.exerciseMessage = v.findViewById(R.id.exercise_message);
        this.exerciseMessage.setText(String.format("Perform %d %s",
                this.exercise.reps,this.exercise.exercise.getExerciseName()));
        this.getDescBtn=v.findViewById(R.id.get_desc_btn);
        this.getDescBtn.setOnClickListener(view-> {
            DialogBoxHelper.createPositiveDialog(getContext(), getResources().getText(R.string.exercise_desc),
                    this.exercise.exercise.getExerciseDescription(), null).show();
        });
        return v;
    }
}