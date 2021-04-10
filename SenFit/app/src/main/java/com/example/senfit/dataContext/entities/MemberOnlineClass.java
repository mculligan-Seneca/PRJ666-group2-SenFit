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
}
