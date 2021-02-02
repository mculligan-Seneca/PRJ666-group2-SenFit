/**
 PRJ666 Sen-Fit
 init date: January 26th 2021
 Author Mitchell Culligan
 Version 1.0
 AddBirthDateFragment Class
 This fragment class extends the dialog fragment class and will provide the user with a seperate dialog window to
 select their birthdate. This fragment will be used in the sign up process
 */
package com.example.senfit;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;


public class AddBirthDateFragment extends DialogFragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String YEAR_ARG = "year_arg";
    public static final String MONTH_ARG = "month_arg";
    public static final String DAY_ARG = "day_arg";

    // TODO: Rename and change types of parameters
    private int year;
    private int month;
    private int day;
    private DatePicker datePicker;
    private Button setBirthDate;
    public interface  BirthDateSaver{//callback interface used to interact with signup page
        public void saveBirthDate(Bundle args);
    }

    private BirthDateSaver dateSaver;//callback interface instance
    public AddBirthDateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param prevDate Parameter 1.
     * .
     * @return A new instance of fragment AddBirthDateFragment.
     */
    // TODO: Rename and change types and number of parameters
    //Takes in previous date user has entered null if none
    public static AddBirthDateFragment newInstance(Date prevDate) {
        AddBirthDateFragment fragment = new AddBirthDateFragment();
        if(prevDate!=null) {
            Bundle args = new Bundle();
            args.putInt(YEAR_ARG, prevDate.getYear()+1900);
            args.putInt(MONTH_ARG, prevDate.getMonth());
            args.putInt(DAY_ARG,prevDate.getDate());
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.year = getArguments().getInt(YEAR_ARG);
            this.month = getArguments().getInt(MONTH_ARG);
            this.day = getArguments().getInt(DAY_ARG);
        }
        else{
            Calendar cal= Calendar.getInstance();
            this.year = cal.get(Calendar.YEAR);
            this.month = cal.get(Calendar.MONTH);
            this.day = cal.get(Calendar.DAY_OF_MONTH);
        }
        //retrieves previous date set by user or instantiates date to today
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof BirthDateSaver){
            this.dateSaver =(BirthDateSaver)context;
        }


    }

    @Override
    public void onDetach(){
        super.onDetach();
        this.dateSaver=null;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_birth_date, container, false);
        this.datePicker= v.findViewById(R.id.date_picker);
        this.setBirthDate=v.findViewById(R.id.set_Birth_Date);
        // TODO:set the original date for date picker
        this.datePicker.setMinDate(Timestamp.valueOf("1970-01-01 00:00:00").getTime());

        this.datePicker.init(this.year, this.month, this.day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int chosenYear, int monthOfYear, int dayOfMonth) {
                year = chosenYear;
                month=monthOfYear;
                day=dayOfMonth;
            }
        });
        this.setBirthDate.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Bundle args = new Bundle();
        args.putInt(YEAR_ARG,this.year);
        args.putInt(MONTH_ARG,this.month);
        args.putInt(DAY_ARG,this.day);
        this.dateSaver.saveBirthDate(args);
        dismiss();

    }
}