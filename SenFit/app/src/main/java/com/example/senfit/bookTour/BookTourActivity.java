/*Portia siddiqua 107741175*/

package com.example.senfit.bookTour;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.senfit.NetworkManager.NetworkManager;
import com.example.senfit.NetworkManager.NetworkServices.UnregisteredClientService;
import com.example.senfit.R;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.UnregisteredClient;

import java.io.IOException;

import retrofit2.Response;

public class BookTourActivity extends AppCompatActivity {

    private static final String EMAIL_FORMAT="[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_tour);

        TextView firstNameTv = findViewById(R.id.first_name);
        TextView lastNameTv = findViewById(R.id.last_name);
        TextView emailTv = findViewById(R.id.customer_email);
        TextView phoneTv = findViewById(R.id.customer_phone_number);

        DatabaseClient dbClient = DatabaseClient.initDB(getApplicationContext());
        Button submit = findViewById(R.id.submit_button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String errors = "";
                String firstName = firstNameTv.getText().toString();
                String lastName = lastNameTv.getText().toString();
                String email = emailTv.getText().toString();
                String phone = phoneTv.getText().toString();

                if (firstName == null || firstName.isEmpty()) {//TODO: ADD EMAIL REGEX
                    errors += "First name must have a value\n";
                }

                if (lastName == null || lastName.isEmpty()) {//TODO: ADD EMAIL REGEX
                    errors += "Last name must have a value\n";
                }

                if (phone == null || phone.isEmpty() || !phone.matches("\\d+(?:\\.\\d+)?")) {
                    errors += "Invalid phone number\n";
                }

                if (email == null || email.isEmpty()|| !email.matches(EMAIL_FORMAT)) {
                    errors += "Invalid email\n";
                }

                if (!errors.isEmpty()) {

                    showError(errors);
                    return;
                }

                new AsyncTask<Object, Object, Object>() {
                        boolean saved = false;
                        @Override
                        protected Object doInBackground(Object[] objects) {

                            try {

                                UnregisteredClientService service = NetworkManager
                                        .getNetworkManager()
                                        .createNetworkService(UnregisteredClientService.class);
                                Response<UnregisteredClient> response = service.
                                        registerClient(new UnregisteredClient(firstName, lastName, email, phone))
                                        .execute();
                                if(response.isSuccessful()) {
                                    UnregisteredClient client = response.body();
                                    dbClient.getAppDatabase().getUnregisteredClientDAO().insertUnregisteredClient(client);
                                    saved = true;
                                }else
                                    saved=false;
                            } catch (SQLiteConstraintException | IOException ex) {

                                saved = false;
                            }

                            return null;
                        }

                        @Override
                        protected void onPostExecute(Object o) {
                            super.onPostExecute(o);
                            if(saved) {
                                showAlert();
                            } else {
                                showError("Email already exists.");
                            }

                        }
                    }.execute();
            }
        });
    }

    private void showAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation of booking")
                .setMessage("You have been booked for a free workout class. Check your email for further information.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_email)
                .show();
    }

    private void showError(String errors) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(errors)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_email)
                .show();
    }

}
