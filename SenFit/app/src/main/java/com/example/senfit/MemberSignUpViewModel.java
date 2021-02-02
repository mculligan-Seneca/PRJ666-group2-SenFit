/*
PRJ666 Sen-Fit
init date: January 28th 2021
Author Mitchell Culligan
Version 1.0
MemberSignUpViewModel
This viewmodel class is meant to store the member data for the sign up use case
 */
package com.example.senfit;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MemberSignUpViewModel extends ViewModel {
//Not using livedate because not expecting data to change
    private Member memberData;


    public MemberSignUpViewModel(){
         this.memberData = new Member();

    }

    public Member getMember(){
        return this.memberData;
    }





    public void insertMember(Context context){
        //TODO: Complete insert or delete
    }



    //TODO: Add functionality to sbmit member
}
