/*
PRJ666 Sen-Fit
init date: April 9th 2021
Author Mitchell Culligan
Version 1.0
MemberOnlineClass
This entity repersents a bridge table for a member to enroll into a online class
 */
package com.example.senfit.dataContext.entities;

import com.google.gson.annotations.SerializedName;

public class MemberOnlineClass {

    public MemberOnlineClass(int memberId, int gymClassId) {
        this.memberId = memberId;
        this.onlineClassId = gymClassId;
    }

    @SerializedName("member_id")
    public int memberId;

    @SerializedName("onlineClassId")
    public int onlineClassId;
}
