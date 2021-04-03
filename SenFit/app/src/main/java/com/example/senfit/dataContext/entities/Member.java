/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
Member class
 */
package com.example.senfit.dataContext.entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Index;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;

//Member entity repersents all the data to be used for a member account
@Entity(tableName="members",indices = {@Index(value = "email",unique = true)})
public class Member {
    @PrimaryKey
    @SerializedName("id")
    private int member_id;//primary key for member class will auto generate a val

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name="last_name")
    private String lastName;

    @ColumnInfo(name="postal_code")
    private String postalCode;
    @ColumnInfo(name="birth_date")
    @SerializedName("birth_date")
    private Date dateOfBirth;// date uses java.sql.Date format

    @ColumnInfo(name="gender")
    private char gender;//gender can either be repersented in F or M

    @ColumnInfo(name="email")

    private String email;

    @ColumnInfo(name="password")
    private String password;//will be hashed
    //must contain at least 1 number, 1 upper case letter and be 8 chars long

    @ColumnInfo(name="salt")
    private String salt;// used as a salt for hashing password
    //TODO: add constructor

    @ColumnInfo(name="token")
    private String token;

    public Member(){
        this.member_id=-1;
        this.firstName=null;
        this.lastName=null;
        this.dateOfBirth=null;
        this.postalCode=null;
        this.gender='N';
        this.email=null;
        this.password=null;
        this.salt=null;
        this.token=null;

    }
    public Member(int member_id,String firstName, String lastName, Date dateOfBirth, String postalCode,
                  String gender,String email,String password,String salt, String token){
        this.member_id=member_id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.dateOfBirth=dateOfBirth;
        this.postalCode=postalCode;
        this.gender=gender.charAt(0);
        this.email=email;
        this.password=password;
        this.salt=salt;
        this.token=token;

    }
    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){return this.email;}
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
