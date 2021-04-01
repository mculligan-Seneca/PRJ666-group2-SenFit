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

import android.app.Application;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {
    public static final String BASE_URL="https://limitless-woodland-96285.herokuapp.com/";
    private  Retrofit retrofit=null;

    private static NetworkManager networkManager=null;

    private NetworkManager(String url){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(url)//URL for senfit API
                .addConverterFactory(GsonConverterFactory.create())//convert JSON to java objects
                .client(new OkHttpClient.Builder().build())
                .build();
            
    }
    public  <T> T  createNetworkService(Class<T> serviceClass){

        return this.retrofit.create(serviceClass);
    }

    public static NetworkManager getNetworkManager(){
        if(networkManager==null){
            networkManager = new NetworkManager(BASE_URL);
        }

        return networkManager;
    }


    public void addInterceptorToClient(Interceptor interceptor){
        this.retrofit.newBuilder()
                .client(new OkHttpClient.Builder().addInterceptor(interceptor).build())
                .build();//TODO Lookup add authenticator
    }
}
