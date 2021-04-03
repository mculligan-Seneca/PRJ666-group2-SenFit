/*
PRJ666 Sen-Fit
init date: March 30th 2021
Author Mitchell Culligan
Version 1.0
AuthInterceptor
This class will intercept requests and append the token needed for authentication
 */
package com.example.senfit.NetworkManager.Interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthInterceptor implements Interceptor {
    private String token;

    public AuthInterceptor(String token){
        this.token=token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder reqBuilder = request.newBuilder().addHeader("Authorization","JWT "+this.token);
        //add JWT with header
        return chain.proceed(reqBuilder.build());
    }
}
