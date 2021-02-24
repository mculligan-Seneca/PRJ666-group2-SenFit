/*
PRJ666 Sen-Fit
init date: Feb 23rd 2021
Author Mitchell Culligan
Version 1.0
AddFitnessResultFragment
This fragment class provides user with exercise to perform and then saves their heart beats in a viewmodel
 */
package com.example.senfit.fitnessResult.addFitnessResults;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.senfit.R;
import com.example.senfit.fitnessResult.ConfirmResultsFragment;


public class AddFitnessResultFragment extends Fragment implements View.OnClickListener {

    private static final String HEART_BEAT_TAG="heart_beats";
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private EditText heartBeatText;
    private TextView exerciseText;
    private ImageButton nextButton;
    private AddFitnessResultViewModel fitnessResultViewModel;
    private NavController navController;
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
        this.heartBeatText.setText(String.valueOf(heartBeatPM));
        this.nextButton = v.findViewById(R.id.next_button);
        this.nextButton.setOnClickListener(this);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fitnessResultViewModel = new ViewModelProvider(getActivity()).get(AddFitnessResultViewModel.class);
        this.fitnessResultViewModel.getExerciseLiveData().observe(getViewLifecycleOwner(),ex->{
           exerciseText.setText(String.format("Perform %d %s",ex.reps,ex.exercise.getExerciseName()));
        });

        this.navController = Navigation.findNavController(view);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(HEART_BEAT_TAG,this.heartBeatPM);
    }

    @Override
    public void onClick(View v) {

        if(isValidated()){
            this.heartBeatPM = Integer.parseInt(this.heartBeatText.getText().toString());
            this.fitnessResultViewModel.addResult(this.heartBeatPM);
            if(this.fitnessResultViewModel.hasNext()){
                this.navController.navigate(R.id.action_result_to_next_result);
            }
            else{
                Bundle args = new Bundle();
                args.putInt(ConfirmResultsFragment.CONFIRM_RESULT_TAG,this.fitnessResultViewModel.getPortfolioId());
                this.navController.navigate(R.id.action_result_to_display,args);
                //TODO: PASS data to view being navigated
            }
        }
        else
        {
            Toast.makeText(getContext(),R.string.heart_beat_errMssg,Toast.LENGTH_LONG).show();
            //TODO: Create validator class to not repeat code

        }
    }

    public boolean isValidated(){
        String heartBeatStr=this.heartBeatText.getText().toString();
        return  !heartBeatStr.isEmpty() && Integer.parseInt(heartBeatStr)>0;

    }
}