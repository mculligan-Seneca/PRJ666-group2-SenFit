/*
 PRJ666 Sen-Fit
 init date: Feb 11th 2021
 Author Mitchell Culligan
 Version 1.0
DialogBoxHelper class
The purpose of this class is to provide the ui functionality of an alert dialog box to multiple ui's
 */
package com.example.senfit.uiHelpers;

import android.content.Context;
import android.content.DialogInterface;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class DialogBoxHelper {
    //creates a dialog with a single positive button
    public static AlertDialog createPositiveDialog(Context context, CharSequence title, CharSequence
            message, DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(title);
        builder.setMessage(message)
                .setCancelable(true)
                .setPositiveButton("Ok", listener);


        return builder.create();
    }

    public static AlertDialog createPositiveDialog(Context context, int titleId, int
            messageId,@Nullable DialogInterface.OnClickListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle(titleId);
        builder.setMessage(messageId)
                .setCancelable(true)
                .setPositiveButton("Ok", listener);


        return builder.create();
    }
}
