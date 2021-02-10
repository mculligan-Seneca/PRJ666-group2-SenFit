/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
DatabaseClient class
This Database client class is a client that instantiates the database
and allows activities of the app to interact with the database.
 */
package com.example.senfit.DataContext;


import android.content.Context;

import androidx.room.Room;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observer;

public class DatabaseClient {
    private AppDatabase appDatabase;
    private static DatabaseClient dbClient=null;

    public static final ExecutorService dbExecutors = Executors.newFixedThreadPool(4);
    //NOTE: Optionally use livedata and observables to run concurrent actions
    private DatabaseClient(Context context){
            this.appDatabase= Room.databaseBuilder(context, AppDatabase.class,"Sen-FitDB").build();

    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static DatabaseClient getInstance(Context context){
        if(dbClient==null)
            dbClient = new DatabaseClient(context);
        //instaniates database client if null
        return dbClient;
    }


}
