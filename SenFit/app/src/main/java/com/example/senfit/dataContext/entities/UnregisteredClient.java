/*
PRJ666 Sen-Fit
init date: February 20th 2021
Author Portia Siddiqua
Version 1.0
UnregisteredClient class
 */
package com.example.senfit.dataContext.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName="UnregisteredClient",indices = {@Index(value = "email",unique = true)})

public class UnregisteredClient {
    @PrimaryKey(autoGenerate = true)
    private int client_id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name="last_name")
    private String lastName;

    @ColumnInfo(name="email")
    private String email;

    @ColumnInfo(name="phone")
    private String phone;

    @Ignore
    private String token;

    public UnregisteredClient(String firstName, String lastName, String email, String phone, String token) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.token = token;
    }
    public UnregisteredClient(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.token = null;
    }
    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
