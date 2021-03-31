/*
PRJ666 Sen-Fit
init date: March 30th 2021
Author Mitchell Culligan
Version 1.0
LoginService
This interface interats with the api to log the user in
 */
package com.example.senfit.NetworkManager.NetwrokServices;

import com.example.senfit.dataContext.entities.Member;
import com.example.senfit.login.LoginBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/login")
    public Call<Member> login(@Body LoginBody loginBody);
}
