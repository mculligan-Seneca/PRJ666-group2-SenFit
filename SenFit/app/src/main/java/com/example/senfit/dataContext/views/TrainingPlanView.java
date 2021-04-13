/*
PRJ666 Sen-Fit
init date: April 9th 2021
Author Mitchell Culligan
Version 1.0
TrainingPlanView
This view repersents how a trainingplan will be displayed in the view
 */
package com.example.senfit.dataContext.views;


import androidx.room.ColumnInfo;
import androidx.room.DatabaseView;

import java.sql.Date;
@DatabaseView(viewName = "trainingPlanView",value = "select tp.trainingPlanId, tp.start_date, f.fitnessPortfolioId, t.trainerId,"+
        " m.member_id, m.first_name || 's plan with '|| t.first_name as plan_name, t.email as trainer_email, "+
        " t.first_name || ' '||t.last_name as instructor_name from trainingplans tp join fitnessPortfolio f "+
        "on tp.fitnessPortfolioId=f.fitnessPortfolioId join trainers t"+
        " on tp.trainerId=t.trainerId join members m on tp.member_id=m.member_id;")
public class TrainingPlanView {
    public int trainingPlanId;


    @ColumnInfo(name="start_date")
    public Date startDate;

    @ColumnInfo(name="fitnessPortfolioId")
    public int fitnessPortfolioId;//TODO: add foreignKey

    @ColumnInfo(name="trainerId")
    public int trainerId; //TODO: add foreignKey

    @ColumnInfo(name="member_id")
    public int member_id;



    @ColumnInfo(name="plan_name")
    public String planName;

    @ColumnInfo(name="trainer_email")
    public String trainerEmail;

    @ColumnInfo(name="instructor_name")
    public String instrucotrName;




}
