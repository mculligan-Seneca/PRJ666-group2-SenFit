
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
@Entity(tableName="GymLocations")
public class GymLocation {

    @PrimaryKey(autoGenerate=true)
    private int gymLocationId;

    @ColumnInfo(name="postal_code")
    private String postalCode;

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
}
