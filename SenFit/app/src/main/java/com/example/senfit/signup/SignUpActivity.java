/*
PRJ666 Sen-Fit
init date: January 27th 2021
Author Mitchell Culligan
Version 1.0
SignUp Activity
This activity allows the user to sign up for a new member account. The activity implements the date saver interface
which allows the activity to recieve data from the AddBirthDate fragment.
 */
package com.example.senfit.signup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetwrokServices.SignUpService;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Member;
import com.example.senfit.R;
import com.example.senfit.login.LoginActivity;
import com.example.senfit.uiHelpers.DialogBoxHelper;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//TODO: Add client side validation
public class SignUpActivity extends AppCompatActivity implements AddBirthDateFragment.BirthDateSaver
         {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-YYYY");
    private static final String POSTAL_FORMAT="[A-Za-z]\\d[A-Za-z][ -]?\\d[A-Za-z]\\d";//Regex for postal format
    private static final String EMAIL_FORMAT="[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*";
    //Regex for email format- allows multiple dots in domain and local but rejects two contigious dots
    private static final String PASSWORD_FORMAT="^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z])[A-Za-z0-9]{8,}$";
    //Regex for password- must conatin at least 8 character - must contain 1 upper case - 1 lower case -
    //       1 number
    private static final String ERR_TITLE ="Error Dialog";
    private EditText firstName, lastName,
            postalCode,email,password,rePassword;

    private TextView birthDate;
             private Button submit;
    private Member member;
    private NetworkManager networkManager;



    //this inside class will display any errors found during validation of sign up



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.member = new Member();
        this.networkManager=NetworkManager.getNetworkManager();
        this.firstName = findViewById(R.id.first_name_id);
        this.lastName = findViewById(R.id.last_name_id);
        this.postalCode = findViewById(R.id.postal_code);
        this.email = findViewById(R.id.email);
        this.password = findViewById(R.id.password);
        this.rePassword = findViewById(R.id.re_password);
        this.birthDate = findViewById(R.id.birthDate);
        Button setBirthDate = findViewById(R.id.addBirthDate);
        this.submit = findViewById(R.id.submit_button);
        setBirthDate.setOnClickListener(v -> {
            AddBirthDateFragment fragment =  AddBirthDateFragment.newInstance(member
                    .getDateOfBirth());
            //dialog fragment instance created off previous date of birth
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            fragment.show(transaction,"Add birth date");

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
        String errMsg = getErrorMessage();
        if(!errMsg.equals("")){
            DialogBoxHelper.createPositiveDialog(this,ERR_TITLE,
                    "The following fields have errors:\n"+errMsg,null)
                    .show();
        }else {
            this.member.setFirstName(first);
            this.member.setLastName(last);
            this.member.setPostalCode(postal);
            this.member.setEmail(e_mail);
            this.member.setPassword(pWord);

            submit.setEnabled(false);
            SignUpService signUpService = this.networkManager.createNetworkService(SignUpService.class);
            Call<SignUpResponse> signUpCall = signUpService.signUp(this.member);
            signUpCall.enqueue(new Callback<SignUpResponse>() {
                @Override
                public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                    if(!response.isSuccessful()){
                        String errMsg=null;
                        try{
                            JSONObject jsonErr= new JSONObject(response.errorBody().string());
                            errMsg=jsonErr.getString("errMsg");
                        }catch(Exception e){
                            Log.e("sign_up_res_err",e.getLocalizedMessage());
                            errMsg="ERRORInternal problem";
                        }
                        DialogBoxHelper.createPositiveDialog(SignUpActivity.this,ERR_TITLE,
                                errMsg,null)
                                .show();
                        submit.setEnabled(true);
                    }else{
                        DatabaseClient dbClient = DatabaseClient.initDB(getApplicationContext());
                        DatabaseClient.dbExecutors.execute(()->{
                            dbClient.getAppDatabase()
                                    .getMemberDao()
                                    .insertMember(member);
                        });//inserts member into db
                        startLoginActivity();
                    }
                }

                @Override
                public void onFailure(Call<SignUpResponse> call, Throwable t) {
                            Log.e("sign_up_res_err",t.getLocalizedMessage());
                    DialogBoxHelper.createPositiveDialog(SignUpActivity.this,ERR_TITLE,
                            "Server error occured",null)
                            .show();
                    submit.setEnabled(true);
                }
            });
            /*
            DatabaseClient dbClient = DatabaseClient.initDB(getApplicationContext());
            DatabaseClient.dbExecutors.execute(()-> {
                List<Member> memberList = dbClient//maybe replace with worker class
                        .getAppDatabase()
                        .getMemberDao()
                        .getMembersFromEmail(member.getEmail());
                if (memberList.isEmpty()) {
                    //TODO: HASH PASSWORD
                    // try {
                    //   PasswordHasher ph = new PasswordHasher();
                    // String hash = new String(ph.hashPassword(member.getPassword()), "UTF-8");
                    member.setPassword(member.getPassword());
                  //  member.setSalt(member.getPassword().);//store salt in database
                    dbClient.getAppDatabase()
                            .getMemberDao()
                            .insertMember(member);
                    runOnUiThread(() -> {

                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

                        Toast.makeText(SignUpActivity.this,"Member successfully created",Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();//activity clean up

                    });

                } else {
                    runOnUiThread(() -> {

                                DialogBoxHelper.createPositiveDialog(SignUpActivity.this,ERR_TITLE,
                                        "Email already found in database",null)
                                .show();
                        submit.setEnabled(true);
                    });
                }
            });*/
        }

    }

    @Override
    public void saveBirthDate(Bundle args) {
        int year = args.getInt(AddBirthDateFragment.YEAR_ARG);
        int month = args.getInt(AddBirthDateFragment.MONTH_ARG);
        int day = args.getInt(AddBirthDateFragment.DAY_ARG);
        Date bDate = new Date(year-1900,month,day);
        birthDate.setText(DATE_FORMAT.format(bDate));
        this.member.setDateOfBirth(bDate);

    }


    private String getErrorMessage() {
        String errors = "";
        String first = this.firstName.getText().toString();
        String last = this.lastName.getText().toString();
        String postal = this.postalCode.getText().toString();
        String e_mail = this.email.getText().toString();
        String pWord = this.password.getText().toString();
        String rpWord = this.rePassword.getText().toString();
        if (first == null || first.isEmpty()) {
            errors += "First name field must have value\n";
        }
        if (last == null || last.isEmpty()) {
            errors += "last name field must have value\n";
        }

        if (this.member.getDateOfBirth() == null) {
            errors += "Date field must be valid\n";
        }

        if (this.member.getGender() == 'N') {
            errors += "Gender field must have value\n";
        }



            if (postal == null || postal.isEmpty() || !postal.matches(POSTAL_FORMAT)) {//TODO:add back postal
                errors += "Postal code field must be formatted correctly\n";

            }
            if (e_mail == null || e_mail.isEmpty()|| !e_mail.matches(EMAIL_FORMAT)) {//TODO: ADD EMAIL REGEX
                errors += "Email field must have value\n";
            }

            if (pWord == null || pWord.isEmpty() || !pWord.matches(PASSWORD_FORMAT)) {//TODO: add pWord regex
                errors += "Password field must have\n - 1 number\n- 1 upper case\n- 1 lower case\n - must have 8 or more characters\n";
            } else if (rpWord == null || rpWord.isEmpty()) {
                errors += "Password must be reentered\n";
            } else if (!pWord.equals(rpWord)) {
                errors += "Password fields must match\n";
            }

            return errors;
        }


        public void startLoginActivity(){
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);

            Toast.makeText(SignUpActivity.this,"Member successfully created",Toast.LENGTH_LONG).show();
            startActivity(intent);
            finish();
        }


}