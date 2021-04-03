/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
Trainer class
 */
package com.example.senfit.dataContext.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "trainers",foreignKeys = {@ForeignKey(entity = GymLocation.class,
        parentColumns = "gymLocationId", childColumns = "gymLocationId")})
public class Trainer {
    @PrimaryKey
    @SerializedName("id")
    private int trainerId;
    //TODO: add constructor and getter and setters
    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    private String firstName;

    @ColumnInfo(name="last_name")
    @SerializedName("last_name")
    private String lastName;


    @ColumnInfo(name="gymLocationId")
    private int gymLocationId;

    @ColumnInfo(name="email")
    private String email;



    public Trainer(){
        this.trainerId=-1;
        this.gymLocationId=-1;
        this.firstName=null;
        this.lastName=null;
        this.email=null;
    }

    public Trainer(int trainerId,String firstName, String lastName,int gymLocationId, String email){
        this.trainerId=trainerId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.gymLocationId=gymLocationId;
        this.email=email;
    }
    public int getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getGymLocationId() {
        return gymLocationId;
    }

    public void setGymLocationId(int gymLocationId) {
        this.gymLocationId = gymLocationId;
    }
}
