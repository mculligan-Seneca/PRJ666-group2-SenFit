/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.ui.inperson;

import android.content.Intent;
import android.os.Bundle;

import com.example.senfit.R;
import com.example.senfit.covidLog.CovidSurveyActivity;
import com.example.senfit.login.LoginHelper;
import com.example.senfit.uiHelpers.DialogBoxHelper;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

public class SenFitActivity extends AppCompatActivity implements InpersonFragment.SelectClassListener {

    private static final int SURVEY_ACTIVITY=2;
    private static final String DEFAULT_FRAG="HOME_FRAGMENT";
    private DrawerLayout drawerLayout;
    private FragmentManager fm;
    private int memberId;
    private int inPersonClassId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senfit);
        this.fm = getSupportFragmentManager();
        this.drawerLayout = findViewById(R.id.drawer_view_senfit);
/*
        if(savedInstanceState==null){
            fm.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frame_layout_senfit,HomeFragment.newInstance(),DEFAULT_FRAG)//sets fragment to home fragment by default
                    .commit();
        }
        this.drawer = findViewById(R.id.drawer_view_senfit);
        NavigationView navView = findViewById(R.id.navigation_viewId);
        navView.setNavigationItemSelectedListener((item)->{

            return true;
        });

        Toolbar toolbar = findViewById(R.id.title_toolBar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,this.drawer,toolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);//to manage functionality of drawer
        this.drawer.addDrawerListener(toggle);
        toggle.syncState();//manages rotating the hamburger icon
        */
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this,this.drawerLayout,
                        R.string.nav_drawer_open,R.string.nav_drawer_close);
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavController navController = Navigation.findNavController(this,R.id.fragment_container_view_tag);;
    /*    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph())
                .setOpenableLayout(drawerLayout)
                .build();*/

       //Toolbar toolbar = findViewById(R.id.title_toolBar);
        NavigationView navView = findViewById(R.id.navigation_viewId);
      //  NavigationUI.setupActionBarWithNavController(this,navController,appBarConfiguration);
        NavigationUI.setupWithNavController(navView,navController);

        this.memberId= LoginHelper.MEMBER_ID;
        this.inPersonClassId=0;
    }

    @Override
    public void onBackPressed(){// when the back button is pressed we do not want to leave activity immeadiatley
        if(this.drawerLayout.isDrawerOpen(GravityCompat.START)){//check if drawer is open
            this.drawerLayout.closeDrawer(GravityCompat.START);
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