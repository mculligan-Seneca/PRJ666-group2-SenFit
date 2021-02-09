/*
 * author: Portia siddiqua(107741175)
 *
 * */


package com.example.senfit.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.senfit.R;
import signup.SignUpActivity;
import com.example.senfit.data.DataInsertionManager;
import com.example.senfit.ui.inperson.SenFitActivity;


/**
 * This class shows UI to input email and password.
 * Use a helper class to validate the credentials
 */
public class LoginActivity extends AppCompatActivity {

    /*
    Interface to get email password comparison result
     */
    public interface ComparisonCallback {
        void isValid(boolean vaild);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DataInsertionManager.insertDummyData(this);
    }

    /*
    Is called when click login button
    Compare email and password with the database counterpart
     */
    public void onLoginClick(View view) {
        EditText userNameEditText = findViewById(R.id.login_username);
        EditText passwordEditText = findViewById(R.id.login_password);
        String userName = userNameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        LoginHelper.compareEmailPass(this, userName, password, new ComparisonCallback() {
            @Override
            public void isValid(boolean vaild) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (vaild) {
                            Toast.makeText(LoginActivity.this, "Loging success", Toast.LENGTH_LONG).show();
                            showSenfitActivity();
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid credentials.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    /*
    Is called whern click singup button
    Shows the signup activity
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
}