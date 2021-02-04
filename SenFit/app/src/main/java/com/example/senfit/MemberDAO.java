/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
MemberDAO class
This interface repersents the data access object of the member class
 */
package com.example.senfit;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.senfit.Entities.Member;

import java.util.List;

@Dao
public interface MemberDAO {

    @Insert
    public void insertMember(Member member);

    @Query("Select * from members where member_id=:id")
    public Member getMember(int id);//IMPORTANT: Methods that retrieve data from database should
    // never be run from main thread

    @Query("Select * from members where email=:email")
    public List<Member> getMembersFromEmail(String email);
    //This method retrieves all members that have the email of the parameter
    //used for user validation


   //TODO: ADD methods for retrieving associated data
}
