/*
 * author: Portia siddiqua(107741175)
 *
 * */


package com.example.senfit.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.senfit.NetworkManager.Interceptor.AuthInterceptor;
import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetwrokServices.LoginService;
import com.example.senfit.R;
import com.example.senfit.bookTour.BookTourActivity;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Member;
import com.example.senfit.signup.SignUpActivity;
import com.example.senfit.data.DataInsertionManager;
import com.example.senfit.ui.inperson.SenFitActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


/**
 * This class shows UI to input email and password.
 * Use a helper class to validate the credentials
 */
public class LoginActivity extends AppCompatActivity {

    /*
    Interface to get email password comparison result
     */
    public interface ComparisonCallback {
        void isValid(boolean validUser, String msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DataInsertionManager.insertDummyData(this);
        onAutomaticLogin();//attempts to login previous owner
    }

    /*
    Is called when click login button
    Compare email and password with the database counterpart
     */
    public void onLoginClick(View view) {
        EditText userNameEditText = findViewById(R.id.first_name);
        EditText passwordEditText = findViewById(R.id.last_name);
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (userName.isEmpty()|| password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Insert valid username and password.", Toast.LENGTH_LONG).show();
            return;
        }


        LoginHelper.compareEmailPass(this, userName, password, new ComparisonCallback() {
            @Override
            public void isValid(boolean vaildUser, String msg) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (vaildUser) {
                            Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                            showSenfitActivity();
                        } else {

                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    /*
    Is called whern click singup button
    Shows the com.example.senfit.signup activity
     */
    public void onSignupClick(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    private void showSenfitActivity() {
        Intent intent = new Intent(this, SenFitActivity.class);
        startActivity(intent);
        finish();
    }

    public void onAutomaticLogin(){
        DatabaseClient.dbExecutors.execute(()->{
            Member member=DatabaseClient.initDB(getApplicationContext())
                    .getAppDatabase()
                    .getMemberDao()
                    .getMember();
            if(member!=null){
                LoginService loginService = NetworkManager.getNetworkManager()
                        .createNetworkService(LoginService.class);
                Call<Member> memberCall = loginService.login(new LoginBody(member.getEmail(),member.getPassword()));
                try {
                    Response response = memberCall.execute();
                    if(response.isSuccessful()){// if member in db valid
                        member=(Member)response.body();//then update member in db with token
                        NetworkManager.getNetworkManager()
                                .addInterceptorToClient(new AuthInterceptor(member.getToken()));
                        DatabaseClient.getInstance()
                                .getAppDatabase()
                                .getMemberDao()
                                .updateMember(member);
                        runOnUiThread(()->{//start next activity with logged member
                            showSenfitActivity();
                        });
                    }
                }catch (IOException ioe){
                    Log.e("login_activity_err",ioe.getMessage());
                }
                catch (Exception e){//mainly for bad cast
                    Log.e("login_activity_err",e.getMessage());
                }
            }
        });//for automatic login
        //if member was previously logged in or found in database
        //system automatically attempts to authenticate user
    }
    public void onBookTourClick(View view) {
        Intent intent = new Intent(this, BookTourActivity.class);
        startActivity(intent);
    }

}