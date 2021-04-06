package com.example.senfit.trainingPlan.selectGymTraining;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;
import com.example.senfit.trainingPlan.EnrollTrainingPlanViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectTrainingGymFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectTrainingGymFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView recyclerView;
    private EnrollTrainingPlanViewModel viewModel;

    public SelectTrainingGymFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_training_gym, container, false);
    }
}