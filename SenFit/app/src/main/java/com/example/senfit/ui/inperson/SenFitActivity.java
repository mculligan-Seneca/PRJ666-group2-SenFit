/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.ui.inperson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.senfit.R;
import com.example.senfit.covidLog.CovidSurveyActivity;
import com.example.senfit.login.LoginHelper;
import com.example.senfit.uiHelpers.DialogBoxHelper;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class SenFitActivity extends AppCompatActivity implements InpersonFragment.SelectClassListener {

    private static final int SURVEY_ACTIVITY=2;
    private int memberId;
    private int inPersonClassId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senfit);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        this.memberId= LoginHelper.MEMBER_ID;
    }

    @Override
    public void selectClassItem(int inPersonClassId) {
            Intent intent = new Intent(this, CovidSurveyActivity.class);
            intent.putExtra(CovidSurveyActivity.MEMBER_ID_TAG,this.memberId);//TODO: Find way to store member id
            startActivityForResult(intent, SURVEY_ACTIVITY);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==SURVEY_ACTIVITY){
            if(resultCode==RESULT_OK){
                //TODO: switch activity to inperson enrollment
                DialogBoxHelper.createPositiveDialog(this,"Status updated successfully",
                        "Title",null).show();
            } else if(resultCode==RESULT_CANCELED){
                int messId = data.getIntExtra(CovidSurveyActivity.CANCELED_RESULT,R.string.default_err_msg);
                DialogBoxHelper.createPositiveDialog(this,messId,R.string.covid_form_dialog,null)
                        .show();
            }
        }

    }
}