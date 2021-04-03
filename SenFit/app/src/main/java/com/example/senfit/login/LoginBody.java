/*
PRJ666 Sen-Fit
init date: March 30th 2021
Author Mitchell Culligan
Version 1.0
LoginBody
This class repersents the body data being sent back to the rest api for login use case.
 */
package com.example.senfit.login;

public class LoginBody {

    public String email;

    public String password;

    public LoginBody(String email,String password){
        this.email=email;
        this.password=password;
    }
}
