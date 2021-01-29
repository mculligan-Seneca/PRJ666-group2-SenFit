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

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;

//TODO: Add client side validation
public class SignUpActivity extends AppCompatActivity implements AddBirthDateFragment.BirthDateSaver {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("DD-MM-YYYY");

    private EditText firstName, lastName,
            postalCode,email,password,rePassword;

    private TextView birthDate;
    private Button setBirthDate;
    private Member member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.member = new Member();
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
                AddBirthDateFragment fragment =  AddBirthDateFragment.newInstance(member.getDateOfBirth());
                //dialog fragment instance created off previous date of birth
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                fragment.show(transaction,"Add birth date");

            }
        });


    }

    public void radioClick(View v){

        switch(v.getId()){
            case R.id.male_genderID:
                this.member.setGender('M');
                break;
            case R.id.female_genderID:
                this.member.setGender('F');
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
        this.member.setFirstName(first);
        this.member.setLastName(last);
        this.member.setPostalCode(postal);
        this.member.setEmail(e_mail);
        this.member.setPassword(pWord);

    }
    @Override
    public void saveBirthDate(Bundle args) {
        int year = args.getInt(AddBirthDateFragment.YEAR_ARG);
        int month = args.getInt(AddBirthDateFragment.MONTH_ARG);
        int day = args.getInt(AddBirthDateFragment.DAY_ARG);
        Date bDate = new Date(year,month,day);
        birthDate.setText(DATE_FORMAT.format(this.member.getDateOfBirth()));
        this.member.setDateOfBirth(bDate);

    }
}