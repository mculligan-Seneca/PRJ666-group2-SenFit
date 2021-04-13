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
    @SerializedName("member_id")
    private int memberId;

    @SerializedName("onlineClassId")
    private int onlineClassId;
    
    public MemberOnlineClass(int memberId, int gymClassId) {
        this.memberId = memberId;
        this.onlineClassId = gymClassId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public int getOnlineClassId() {
        return onlineClassId;
    }

    public void setOnlineClassId(int onlineClassId) {
        this.onlineClassId = onlineClassId;
    }


}
