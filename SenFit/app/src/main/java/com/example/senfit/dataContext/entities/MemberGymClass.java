/*
PRJ666 Sen-Fit
init date: April 9th 2021
Author Mitchell Culligan
Version 1.0
MemberGymClass
This entity repersents a bridge table for a member to enroll into a gym class
 */
package com.example.senfit.dataContext.entities;

import com.google.gson.annotations.SerializedName;

public class MemberGymClass {

    @SerializedName("member_id")
    private int memberId;

    @SerializedName("gymClassId")
    private int gymClassId;

    public MemberGymClass(int memberId, int gymClassId) {
        this.memberId = memberId;
        this.gymClassId = gymClassId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getGymClassId() {
        return gymClassId;
    }

    public void setGymClassId(int gymClassId) {
        this.gymClassId = gymClassId;
    }
}
