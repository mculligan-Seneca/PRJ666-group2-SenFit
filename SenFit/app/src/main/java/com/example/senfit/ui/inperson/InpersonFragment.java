  /*
   * author: Portia siddiqua(107741175)
   *
   * */


  package com.example.senfit.ui.inperson;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.senfit.PageViewModel;
import com.example.senfit.R;
import com.example.senfit.covidLog.CovidSurveyActivity;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.login.LoginHelper;
import com.example.senfit.uiHelpers.DialogBoxHelper;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

  /**
 * A placeholder fragment containing a simple view.
 */
public class InpersonFragment extends Fragment implements InpersonAdapter.SelectClassListener {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    protected RecyclerView mRecyclerView;
    protected InpersonAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private int mInPersonClassId;
    InpersonViewModel model;

      private static final int SURVEY_ACTIVITY=2;
      private int memberId;

      private int mCurrentPosition;

      @Override
      public void selectClassItem(int pos, int inPersonClassId) {
          mInPersonClassId = inPersonClassId;
          mCurrentPosition = pos;
          Intent intent = new Intent(getContext(), CovidSurveyActivity.class);
          //intent.putExtra(CovidSurveyActivity.MEMBER_ID_TAG,this.memberId);
          startActivityForResult(intent, SURVEY_ACTIVITY);
      }


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
        this.memberId= LoginHelper.getMemberId(getContext());
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
        model = new ViewModelProvider(this).get(InpersonViewModel.class);

        List<InpersonClassData> data = new ArrayList<>();

        mAdapter = new InpersonAdapter(data , this);//passes data and listener to adapter

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(mLayoutManager);

        // Observing live data and updating adapter data
        model.getInpersonClasses().observe(this, inpersonClassData -> {
            mAdapter.updateDataSet(inpersonClassData);
        });

        return root;
    }

      @Override
      public void onDestroy() {
          super.onDestroy();
          Log.d("INPERSON_FRAGMENT", "Fragment Destroyed");

      }

      @Override
      public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
          super.onActivityResult(requestCode, resultCode, data);

          if (requestCode == SURVEY_ACTIVITY) {
              if (resultCode == RESULT_OK) {
                  showConfirmationDialog();
              } else if (resultCode == RESULT_CANCELED) {
                  int messId = data.getIntExtra(CovidSurveyActivity.CANCELED_RESULT, R.string.default_err_msg);
                  DialogBoxHelper.createPositiveDialog(getContext(), messId, R.string.covid_form_dialog, null)
                          .show();
              }
          }
      }


      public void showConfirmationDialog(){
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
                  DatabaseClient.initDB(getContext()).getAppDatabase().getGymClassDao().updateEnrollStatus(mInPersonClassId, true);
                  return null;
              }

              @Override
              protected void onPostExecute(Void aVoid) {
                  super.onPostExecute(aVoid);

                  synchronized (mAdapter) {
                      model.getInpersonClasses().observe(InpersonFragment.this, inpersonClassData -> {
                          inpersonClassData.get(mCurrentPosition).setEnrolled(true);
                          mAdapter.updateDataSet(inpersonClassData);
                          Toast.makeText(getContext(),"You have enrolled successfylly.", Toast.LENGTH_LONG).show();
                      });
                  }

              }
          }.execute();
      }
  }