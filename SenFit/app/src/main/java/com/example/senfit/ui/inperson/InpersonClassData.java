package com.example.senfit.ui.inperson;

import android.graphics.Bitmap;

/*
 * author: Portia siddiqua(107741175)
 *
 *  * */
public class InpersonClassData {
    private String clasName;
    private String date;
    private String time;
    private String instructorName;
    private Bitmap image;

    public String getClasName() {
        return clasName;
    }

    public void setClasName(String clasName) {
        this.clasName = clasName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
