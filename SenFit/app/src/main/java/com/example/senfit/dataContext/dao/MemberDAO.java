/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
MemberDAO class
This interface repersents the data access object of the member class
 */
package com.example.senfit.dataContext.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.senfit.dataContext.entities.Member;

import java.util.List;

@Dao
public interface MemberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public Long insertMember(Member member);//returns new rownumber

    //use maybe
    @Query("Select * from members limit 1")
    public Member getMember();//IMPORTANT: Methods that retrieve data from database should
    // never be run from main thread

    @Query("Select * from members where email=:email")
    public List<Member> getMembersFromEmail(String email);
    //This method retrieves all members that have the email of the parameter
    //used for user validation


    @Update
    public void updateMember(Member member);
   //TODO: ADD methods for retrieving associated data
}
