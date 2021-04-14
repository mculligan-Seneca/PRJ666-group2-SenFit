
/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
GymLocation class
 */
package com.example.senfit.dataContext.entities;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="GymLocations")
public class GymLocation {

    @PrimaryKey
    @SerializedName("id")
    private int gymLocationId;

    @ColumnInfo(name="postal_code")
    @SerializedName("postal_code")
    private String postalCode;

    @ColumnInfo(name="street_address")
    @SerializedName("street_address")
    private String streetAddress;

    @ColumnInfo(name="province")
    @SerializedName("province")
    private String province;

    public int getGymLocationId() {
        return gymLocationId;
    }

    public void setGymLocationId(int gymLocationId) {
        this.gymLocationId = gymLocationId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
