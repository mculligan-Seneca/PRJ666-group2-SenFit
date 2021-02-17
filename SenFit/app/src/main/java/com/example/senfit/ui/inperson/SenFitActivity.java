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
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

public class SenFitActivity extends AppCompatActivity implements InpersonFragment.SelectClassListener {

    private static final int SURVEY_ACTIVITY=2;
    private DrawerLayout drawer;
    private int memberId;
    private int inPersonClassId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senfit);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Toolbar tool = findViewById(R.id.title_toolBar);
        tool.setTitle(R.string.app_name);
        this.drawer = findViewById(R.id.drawer_view_senfit);
        NavigationView navView = findViewById(R.id.navigation_viewId);
        navView.setNavigationItemSelectedListener((item)->{

            return true;
        });
        setSupportActionBar(tool);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,this.drawer,tool,
                R.string.nav_drawer_open,R.string.nav_drawer_close);//to manage functionality of drawer
        this.drawer.addDrawerListener(toggle);
        toggle.syncState();//manages rotating the hamburger icon
        this.memberId= LoginHelper.MEMBER_ID;
        this.inPersonClassId=0;
    }

    @Override
    public void onBackPressed(){// when the back button is pressed we do not want to leave activity immeadiatley
        if(this.drawer.isDrawerOpen(GravityCompat.START)){//check if drawer is open
            this.drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
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
                DialogBoxHelper.createPositiveDialog(this,"Title",
                        "Status updated successfully",null).show();
                //finish();
            } else if(resultCode==RESULT_CANCELED){
                int messId = data.getIntExtra(CovidSurveyActivity.CANCELED_RESULT,R.string.default_err_msg);
                DialogBoxHelper.createPositiveDialog(this,messId,R.string.covid_form_dialog,null)
                        .show();
            }
        }

    }
}