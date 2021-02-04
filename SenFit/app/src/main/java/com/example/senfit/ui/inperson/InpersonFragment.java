  /*
   * author: Portia siddiqua(107741175)
   *
   * */


  package com.example.senfit.ui.inperson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.DatabaseClient;
import com.example.senfit.FitnessClass;
import com.example.senfit.GymClass;
import com.example.senfit.R;
import com.example.senfit.Trainer;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class InpersonFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    protected RecyclerView mRecyclerView;
    protected InpersonAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected String[] mDataset;


    public static InpersonFragment newInstance(int index) {
        InpersonFragment fragment = new InpersonFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_inperson, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());
        InpersonViewModel model = new ViewModelProvider(this).get(InpersonViewModel.class);

        // Observing live data and updating adapter data
        model.getInpersonClasses(getContext()).observe(this, inpersonClassData -> {
            mAdapter = new InpersonAdapter(inpersonClassData);

            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.setAdapter(mAdapter);

            mRecyclerView.setLayoutManager(mLayoutManager);
        });

        return root;
    }
}