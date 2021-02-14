package com.example.senfit.ui.online;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.senfit.R;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.ui.inperson.InpersonAdapter;
import com.example.senfit.ui.inperson.InpersonClassData;

import java.util.ArrayList;
import java.util.List;

public class OnlineClassFragment extends Fragment implements InpersonAdapter.SelectClassListener {

    private OnlineClassViewModel mViewModel;

    protected RecyclerView mRecyclerView;

    protected OnlineClassAdapter mAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;

    private int mOnlineClassId;


    public static OnlineClassFragment newInstance() {
        return new OnlineClassFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_class_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView2);
        mLayoutManager = new LinearLayoutManager(getActivity());

        List<InpersonClassData> data = new ArrayList<>();

        mAdapter = new OnlineClassAdapter(data , this);//passes data and listener to adapter

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(mLayoutManager);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OnlineClassViewModel.class);

        mViewModel.getOnlineClasses()
                .observe(this, new Observer<List<InpersonClassData>>() {
            @Override
            public void onChanged(List<InpersonClassData> inpersonClassData) {
                mAdapter.updateDataSet(inpersonClassData);
            }
        });



    }

    @Override
    public void selectClassItem(int position, int inPersonClassId) {

    }
}