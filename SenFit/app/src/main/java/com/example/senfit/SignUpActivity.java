/*
PRJ666 Sen-Fit
init date: January 27th 2021
Author Mitchell Culligan
Version 1.0
SignUp Activity
This activity allows the user to sign up for a new member account. The activity implements the date saver interface
which allows the activity to recieve data from the AddBirthDate fragment.
 */
package com.example.senfit;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

//TODO: Add client side validation
public class SignUpActivity extends AppCompatActivity implements AddBirthDateFragment.BirthDateSaver,
        SignUpProcessFragment.SignUpRequest {
    private static final int REQUEST_PROCCESS=1;//request code for sign up process activity
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YYYY");

    private EditText firstName, lastName,
            postalCode,email,password,rePassword;

    private TextView birthDate;
    private Button setBirthDate;
    private MemberSignUpViewModel memberViewModel;

    @Override
    public void resolve() {
        Intent intent = new Intent(this, LoginActivity.class);
        Toast.makeText(this,"Member successfully created",Toast.LENGTH_LONG);
        startActivity(intent);
        finish();//activity clean up
    }

    @Override
    public void reject(String errMsg) {
        new ErrorDialog().buildErrorDialog(SignUpActivity.this,errMsg);
    }


    //this inside class will display any errors found during validation of sign up
    private class ErrorDialog{//TODO: ADD User story
        private static final String ERR_TITLE ="Error Dialog";
        private ArrayList<CharSequence> errorList;

        //constructor takes in list of errors
        public ErrorDialog(CharSequence... errors){
            errorList = new ArrayList<>(Arrays.asList(errors));

        }

        public ErrorDialog(){
            errorList = new ArrayList<>();
        }

        public boolean hasErrors(){
            return !errorList.isEmpty();
        }

        public void pushError(CharSequence error){
            errorList.add(error);
        }
        public AlertDialog buildErrorDialog(Context context){
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(ERR_TITLE);

            if(hasErrors()){
                CharSequence[] errArray = new CharSequence[errorList.size()];
                errArray = errorList.toArray(errArray);
                builder.setMessage("The following fields have errors and must be corrected:")
                        .setItems(errArray,null);

            }else{
                builder.setMessage("There are no errors to report");

            }

            builder.setCancelable(true)
                    .setPositiveButton("Ok",null);

            return builder.create();
        }
        public AlertDialog buildErrorDialog(Context context, CharSequence message){
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(ERR_TITLE);
                builder.setMessage(message)
                        .setCancelable(true)
                        .setPositiveButton("Ok",null);

            return builder.create();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.memberViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication())
                .create(MemberSignUpViewModel.class);//using view model to store data
        this.firstName = findViewById(R.id.first_name_id);
        this.lastName = findViewById(R.id.last_name_id);
        this.postalCode = findViewById(R.id.postal_code);
        this.email = findViewById(R.id.email);
        this.password = findViewById(R.id.password);
        this.rePassword = findViewById(R.id.re_password);
        this.birthDate = findViewById(R.id.birthDate);
        this.setBirthDate = findViewById(R.id.addBirthDate);

        this.setBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBirthDateFragment fragment =  AddBirthDateFragment.newInstance(memberViewModel.getMember()
                        .getDateOfBirth());
                //dialog fragment instance created off previous date of birth
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                fragment.show(transaction,"Add birth date");

            }
        });


    }

    public void radioClick(View v){

        switch(v.getId()){
            case R.id.male_genderID:
                this.memberViewModel.getMember().setGender('M');
                break;
            case R.id.female_genderID:
                this.memberViewModel.getMember().setGender('F');
                break;
        }

    }


    public void submitClick(View v){
        //TODO: Add validation before submit
        String first = this.firstName.getText().toString();
        String last = this.lastName.getText().toString();
        String postal= this.postalCode.getText().toString();
        String e_mail = this.email.getText().toString();
        String pWord = this.password.getText().toString();
        String rpWord = this.rePassword.getText().toString();
        //attributes like date and gender are already set although must be validated
        //After validation
        ErrorDialog errorDialog = this.validate();
        if(errorDialog.hasErrors()){
            errorDialog.buildErrorDialog(this);
        }else {
            this.memberViewModel.getMember().setFirstName(first);
            this.memberViewModel.getMember().setLastName(last);
            this.memberViewModel.getMember().setPostalCode(postal);
            this.memberViewModel.getMember().setEmail(e_mail);
            this.memberViewModel.getMember().setPassword(pWord);

            SignUpProcessFragment fragment = new SignUpProcessFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            fragment.show(transaction,"Add birth date");

        }

    }
    @Override
    public void saveBirthDate(Bundle args) {
        int year = args.getInt(AddBirthDateFragment.YEAR_ARG);
        int month = args.getInt(AddBirthDateFragment.MONTH_ARG);
        int day = args.getInt(AddBirthDateFragment.DAY_ARG);
        Date bDate = new Date(year,month,day);
        birthDate.setText(DATE_FORMAT.format(bDate));
        this.memberViewModel.getMember().setDateOfBirth(bDate);

    }

    private ErrorDialog validate(){
        ErrorDialog errDialog = new ErrorDialog();
        String first = this.firstName.getText().toString();
        String last = this.lastName.getText().toString();
        String postal= this.postalCode.getText().toString();
        String e_mail = this.email.getText().toString();
        String pWord = this.password.getText().toString();
        String rpWord = this.rePassword.getText().toString();
        if(first==null || first.isEmpty()){
            errDialog.pushError("First name field must have value");
        }
        if(last==null || last.isEmpty()){
            errDialog.pushError("last name field must have value");
        }

        if(this.memberViewModel.getMember().getDateOfBirth()==null){
            errDialog.pushError("Date field must be valid");
        }

        if(this.memberViewModel.getMember().getGender()=='N'){
            errDialog.pushError("Gender field must have value");
        }

        if(postal==null || postal.isEmpty() || !postal.matches("[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d")){
            errDialog.pushError("Postal code field must have value and be formatted correctly");
        }
        if(e_mail==null || e_mail.isEmpty()) {//TODO: ADD EMAIL REGEX
            errDialog.pushError("Email field must have value");
        }

        if(pWord==null || pWord.isEmpty()){
            errDialog.pushError("Password field must have value");
        }else if(rpWord==null || rpWord.isEmpty()){
            errDialog.pushError("Password must be reentered");
        }else if(!pWord.equals(rpWord)){
            errDialog.pushError("Password fields must match");
        }

        return errDialog;
    }


}