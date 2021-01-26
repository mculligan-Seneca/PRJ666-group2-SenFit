/*
PRJ666 Sen-Fit
init date: January 24th 2021
Author Mitchell Culligan
Version 1.0
CovidLog class
 */
package com.example.senfit;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.sql.Date;

@Entity(tableName="CovidLogs",
        foreignKeys = @ForeignKey(entity=Member.class,
                                  parentColumns="covidLogID",
                                  childColumns = "member_id"
        ))
public class CovidLog {
    @PrimaryKey(autoGenerate = true)
    private  int covidLogID;

    @ColumnInfo(name="covid_status")
    private boolean status;

    @ColumnInfo(name="date_logged")
    private Date date_logged;

    @ColumnInfo(name="member_id")
    private int member_id;

    public int getCovidLogID() {
        return covidLogID;
    }

    public void setCovidLogID(int covidLogID) {
        this.covidLogID = covidLogID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate_logged() {
        return date_logged;
    }

    public void setDate_logged(Date date_logged) {
        this.date_logged = date_logged;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }
}
