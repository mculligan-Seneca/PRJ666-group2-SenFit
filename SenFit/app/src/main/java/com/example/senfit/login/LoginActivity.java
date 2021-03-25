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
import com.example.senfit.bookTour.BookTourActivity;
import com.example.senfit.signup.SignUpActivity;
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
        void isValid(boolean vaildUser, boolean isValidPass);
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
            public void isValid(boolean vaildUser, boolean validPass) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (vaildUser && validPass) {
                            Toast.makeText(LoginActivity.this, "Loging success", Toast.LENGTH_LONG).show();
                            showSenfitActivity();
                        } else {
                            if (!vaildUser && validPass) {
                                Toast.makeText(LoginActivity.this, "Invalid username.", Toast.LENGTH_LONG).show();
                            } else if(vaildUser && !validPass) {
                                Toast.makeText(LoginActivity.this, "Invalid password.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "Invalid credentials.", Toast.LENGTH_LONG).show();
                            }
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

    public void onBookTourClick(View view) {
        Intent intent = new Intent(this, BookTourActivity.class);
        startActivity(intent);
    }

}