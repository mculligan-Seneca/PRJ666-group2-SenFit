/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.ui.inperson;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.R;
import com.example.senfit.covidLog.CovidSurveyActivity;
import com.example.senfit.fitnessPortfolio.FitnessPortfolioFragment;
import com.example.senfit.login.LoginActivity;
import com.example.senfit.login.LoginHelper;
import com.example.senfit.navigator.NavigateFragment;
import com.example.senfit.navigator.Navigator;
import com.example.senfit.trainingPlan.listTrainingPlan.ListTrainingPlanFragment;
import com.example.senfit.ui.online.OnlineClassFragment;
import com.example.senfit.uiHelpers.DialogBoxHelper;
import com.google.android.material.navigation.NavigationView;

public class SenFitActivity extends AppCompatActivity implements Navigator, NavigateFragment {

    private static final int SURVEY_ACTIVITY=2;
    private static final String DEFAULT_FRAG="HOME_FRAGMENT";
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private FragmentManager fm;
    private int memberId;
    private int inPersonClassId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senfit);
        this.fm = getSupportFragmentManager();
        toolbar = findViewById(R.id.title_toolBar);
       if(savedInstanceState==null){
            fm.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frame_layout_senfit,HomeFragment.newInstance(),DEFAULT_FRAG)//sets fragment to home fragment by default
                    .commit();
            toolbar.setTitle(R.string.app_name);
        }

        String token = LoginHelper.getToken(this);

        NetworkManager.getNetworkManager().addAuthToken(token);


        this.drawerLayout = findViewById(R.id.drawer_view_senfit);
        NavigationView navView = findViewById(R.id.navigation_viewId);
        navView.setNavigationItemSelectedListener((item)->{
            
                switch(item.getItemId()){
                    case R.id.home_fragment:
                        this.replaceFragment(HomeFragment.newInstance(),R.string.app_name);
                        break;

                    case R.id.fitness_port_id:
                        this.replaceFragment(FitnessPortfolioFragment.newInstance(this.memberId),
                                R.string.fitness_portfolio);
                        break;
                    case R.id.inPerson_fragment:
                        this.replaceFragment(InpersonFragment.newInstance(0),R.string.tab_inperson);
                        break;
                    case R.id.online_fragment:
                        this.replaceFragment(OnlineClassFragment.newInstance(),R.string.tab_online);
                        break;
                    case R.id.list_training_plan_fragment:
                        this.replaceFragment(ListTrainingPlanFragment.newInstance(this.memberId),R.string.select_trainer_plan);
                        break;
                    case R.id.log_out:
                        LoginHelper.setLoginStatus(this, false);
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;


                    default:
                        Toast.makeText(SenFitActivity.this,"Button pressed",Toast.LENGTH_LONG).show();
                }



            return true;
        });


        //TODO:get Toolbar from frag,emt


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,this.drawerLayout,toolbar,
                R.string.nav_drawer_open,R.string.nav_drawer_close);//to manage functionality of drawer
        this.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();//manages rotating the hamburger icon

        //Bundle extras = getIntent()
        this.memberId = LoginHelper.getMemberId(this);
        this.inPersonClassId=0;
    }


    public void replaceFragment( Fragment fragment,int fragmentTitle){
        fm.beginTransaction().replace(R.id.frame_layout_senfit,fragment)
                .commit();
        toolbar.setTitle(fragmentTitle);
        this.drawerLayout.closeDrawer(GravityCompat.START);
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

    @Override
    public void navigateTo(Intent intent) {//starts new activity and finishes previous one
        startActivity(intent);
        finish();

    }

    @Override
    public void swapFragment(Fragment fragment, int titleId) {
        fm.beginTransaction()
                .replace(R.id.frame_layout_senfit,fragment)
                .addToBackStack(null).commit();
        toolbar.setTitle(titleId);

    }

    @Override
    public void swapFragment(Fragment fragment, String title) {
        fm.beginTransaction()
                .replace(R.id.frame_layout_senfit,fragment)
                .addToBackStack(null).commit();
        toolbar.setTitle(title);
    }
}