/*
PRJ666 Sen-Fit
init date: January 28th 2021
Author Mitchell Culligan
Version 1.0
MemberSignUpViewModel
This viewmodel class is meant to store the member data for the sign up use case
 */
package com.example.senfit;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MemberSignUpViewModel extends ViewModel {

    private Member memberData;

    public MemberSignUpViewModel(){
         this.memberData = new Member();
    }

    public Member getMember(){
        return this.memberData;
    }







    //TODO: Add functionality to sbmit member
}
