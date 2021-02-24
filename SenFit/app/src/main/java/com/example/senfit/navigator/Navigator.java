/*
PRJ666 Sen-Fit
init date:  Feb 24th 2021
Author Mitchell Culligan
Version 1.0
Navigator interface
This Navigator interface acts as a callback interface for a fragment to communicate with its parent activity and
provide a new activity to be navigated to
 */
package com.example.senfit.navigator;

import android.content.Intent;

public interface Navigator {
    public void navigateTo(Intent intent);
}
