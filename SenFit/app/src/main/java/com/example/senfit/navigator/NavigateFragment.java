/*
PRJ666 Sen-Fit
init date:  March 15th
Author Mitchell Culligan
Version 1.0
Navigate Fragment infterface
interface that allows fragment to interact with activity and replace said fragment
 */
package com.example.senfit.navigator;

import androidx.fragment.app.Fragment;

public interface NavigateFragment {
    public void swapFragment(Fragment fragment,int titleId);

    public void swapFragment(Fragment fragment, String title);
}
