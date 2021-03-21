package com.example.senfit.bookTour;

import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.senfit.R;
import com.example.senfit.dataContext.DatabaseClient;
import com.example.senfit.dataContext.entities.Member;
import com.example.senfit.dataContext.entities.UnregisteredClient;

import java.util.List;

public class BookTourActivity extends AppCompatActivity {

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
                new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        String firstName = firstNameTv.getText().toString();
                        String lastName = lastNameTv.getText().toString();
                        String email = emailTv.getText().toString();
                        String phone = phoneTv.getText().toString();

                        UnregisteredClient client = new UnregisteredClient(firstName, lastName, email, phone);

                        dbClient.getAppDatabase().getUnregisteredClientDAO().insertUnregisteredClient(client);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        showAlert();

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
}
