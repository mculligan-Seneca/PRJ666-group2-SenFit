/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
Trainer class
 */
package com.example.senfit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trainers")
public class Trainer {
    @PrimaryKey(autoGenerate=true)
    private int trainerId;
    //TODO: add constructor and getter and setters
    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name="last_name")
    private String lastName;


    @ColumnInfo(name="post_code")
    private String postalCode;

    @ColumnInfo(name="email")
    private String email;


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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
