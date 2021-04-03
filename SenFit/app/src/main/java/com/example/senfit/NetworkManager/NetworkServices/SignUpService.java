/*
PRJ666 Sen-Fit
init date: March 31st 2021
Author Mitchell Culligan
Version 1.0
SignUpService
This interface is used to interact with the api when the user performs the sign up use case
 */
package com.example.senfit.NetworkManager.NetworkServices;

import com.example.senfit.dataContext.entities.Member;
import com.example.senfit.signup.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignUpService {

    @POST("/signUp")
    public Call<SignUpResponse> signUp(@Body Member member);

}
