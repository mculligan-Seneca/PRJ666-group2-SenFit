/*
PRJ666 Sen-Fit
init date: March 26th 2021
Author Mitchell Culligan
Version 1.0
NetworkManager
This class is created with the purpose of creating services to interact with the rest api
specifically made for Sen-fit. This class does so through RetroFit.
 */
package com.example.senfit.NetworkManager;

import retrofit2.Retrofit;

public class NetworkManager {
    private static final String BASE_URL="https://limitless-woodland-96285.herokuapp.com/";
    private static Retrofit retrofit=null;

}
