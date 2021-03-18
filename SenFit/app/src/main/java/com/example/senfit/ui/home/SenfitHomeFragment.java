package com.example.senfit.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.senfit.R;


public class SenfitHomeFragment extends Fragment {


    public SenfitHomeFragment() {
    }

    public static SenfitHomeFragment newInstance() {
        SenfitHomeFragment fragment = new SenfitHomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

      View view =  inflater.inflate(R.layout.fragment_senfit_home, container, false);

      Button button = view.findViewById(R.id.myClassesButton);

      button.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(getActivity(), MyClassesActivity.class);
              startActivity(intent);
          }
      });

        return view;
    }

}