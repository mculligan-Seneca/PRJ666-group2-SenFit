
/*Author : Portia Siddiqua 107741175*/
package com.example.senfit.ui.online;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.senfit.R;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.OnlineClass;
import com.example.senfit.dataContext.views.OnlineClassView;
import com.example.senfit.ui.inperson.InpersonAdapter;
import com.example.senfit.ui.inperson.InpersonClassData;
import com.example.senfit.ui.inperson.InpersonFragment;

import java.util.ArrayList;
import java.util.List;

public class OnlineClassFragment extends Fragment implements InpersonAdapter.SelectClassListener {

    private OnlineClassViewModel mViewModel;

    protected RecyclerView mRecyclerView;

    protected OnlineClassAdapter mAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;

    private int mOnlineClassId;

    private int mCurrentPosition;


    public static OnlineClassFragment newInstance() {
        return new OnlineClassFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.online_class_fragment, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView2);
        mLayoutManager = new LinearLayoutManager(getActivity());

        List<OnlineClassView> data = new ArrayList<>();

        mAdapter = new OnlineClassAdapter(data , this);

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
                .observe(getViewLifecycleOwner(), new Observer<List<OnlineClassView>>() {
            @Override
            public void onChanged(List<OnlineClassView> onlineClassViews) {
                mAdapter.updateDataSet(onlineClassViews);
            }
        });



    }

    @Override
    public void selectClassItem(int position, int onlineClassId) {
        mCurrentPosition = position;
        mOnlineClassId = onlineClassId;
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enrollment confirmation")
                .setMessage("Do you want to enroll?")
                .setCancelable(true)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        updateEnrollStatusinDB();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }

    private void updateEnrollStatusinDB() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.initDB(getContext()).getAppDatabase().getOnlineClassDao().updateEnrollStatus(mOnlineClassId, true);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                synchronized (mAdapter) {
                    mViewModel.getOnlineClasses().observe(OnlineClassFragment.this, inpersonClassData -> {
                        inpersonClassData.get(mCurrentPosition).enrolled=true;
                        mAdapter.updateDataSet(inpersonClassData);
                        showEnrollSuccessAlertDialog();
                    });
                }

            }
        }.execute();
    }

    private void showEnrollSuccessAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Enrollment status")
                .setMessage("You have enrolled on your online class. Class link will be sent via email.")
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }
}