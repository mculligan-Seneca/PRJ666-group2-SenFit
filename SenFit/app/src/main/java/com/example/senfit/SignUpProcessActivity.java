/*
PRJ666 Sen-Fit
init date: January 31st 2021
Author Mitchell Culligan
Version 1.0
SignUpProgress Activity
This activity is called after the user has completed the signup form.
The purpose of this activity is to provide a wait screen to the user while
the members account is being created in the background.
This activity also provides the safety that the user cannot submit the form multiple times.
 */
package com.example.senfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

public class SignUpProcessActivity extends AppCompatActivity {
    private MemberSignUpViewModel memberViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_process);
        this.memberViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MemberSignUpViewModel.class);//using view model to store data

    }
}