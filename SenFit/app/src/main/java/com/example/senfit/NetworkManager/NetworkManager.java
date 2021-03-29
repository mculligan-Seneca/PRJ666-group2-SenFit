/*
PRJ666 Sen-Fit
init date: March 26th 2021
Author Mitchell Culligan
Version 1.0
NetworkManager
This class is created with the purpose of creating services to interact with the rest api
specifically made for Sen-fit. This class does so through RetroFit. This class implements the singleton deisign pattern
 */
package com.example.senfit.NetworkManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    private static final String BASE_URL="https://limitless-woodland-96285.herokuapp.com/";
    private static Retrofit retrofit=null;


    public static <T> T  createNetworkService(Class<T> serviceClass){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)//URL for senfit API
                    .addConverterFactory(GsonConverterFactory.create())//convert JSON to java objects
                    .client(new OkHttpClient.Builder().build())
                    .build();
        }
        return retrofit.create(serviceClass);
    }

}
