/*
 * author: Portia siddiqua(107741175)
 *
 * */

package com.example.senfit.login;

import android.content.Context;
import android.os.AsyncTask;

import com.example.senfit.DataContext.DatabaseClient;
import com.example.senfit.DataContext.Entities.Member;

import java.util.List;

/**
 * This class fetch login info from database and compare with the user input
 */
public class LoginHelper {

    public LoginHelper() {}

    /*
    Compare user credentials
    context - app context
    email -  input email
    password - input password
    comparisonCallback - interface to propagate result
     */
    public static void compareEmailPass(Context context, String email, String password,
                                        LoginActivity.ComparisonCallback comparisonCallback) {
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            comparisonCallback.isValid(false);
            return;
        }

        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {
                List<Member> memberList = DatabaseClient.getInstance(context)
                        .getAppDatabase().getMemberDao().getMembersFromEmail(email);

                if (memberList == null || memberList.isEmpty()) {
                    comparisonCallback.isValid(false);
                    return null;
                }

                String emailFromDb = memberList.get(0).getEmail();
                String passWordFromDb = memberList.get(0).getPassword();
                comparisonCallback.isValid(email.equalsIgnoreCase(emailFromDb)
                        && password.equals(passWordFromDb));
                return null;
            }
        }.execute();
    }
}
