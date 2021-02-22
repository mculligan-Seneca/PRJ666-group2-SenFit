/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
DatabaseClient class
This Database client class is a client that instantiates the database
and allows activities of the app to interact with the database.
 */
package com.example.senfit.dataContext;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseClient {
    private static final Migration MIGRATION_7_8 = new Migration(7,8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
           database.execSQL("Alter table fitnessportfolio"+
           "modify  height INTEGER;");
            database.execSQL("Alter table fitnessportfolio"+
                    "modify  weight INTEGER;");
        }
    };


    private AppDatabase appDatabase;
    private static DatabaseClient dbClient=null;

    public static final ExecutorService dbExecutors = Executors.newFixedThreadPool(4);
    //NOTE: Optionally use livedata and observables to run concurrent actions
    private DatabaseClient(Context context){
            this.appDatabase= Room.databaseBuilder(context, AppDatabase.class,"Sen-FitDB")
                    //.addMigrations(MIGRATION_3_4)
                    //.fallbackToDestructiveMigration()
                    .build();

    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public static DatabaseClient getInstance(){
        return dbClient;
    }


    public static DatabaseClient initDB(Context context){
        if(dbClient==null)
            dbClient = new DatabaseClient(context);
        //instaniates database client if null
        return dbClient;
    }
}
