package com.example.hpuser.stockmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.security.spec.ECParameterSpec;
import java.util.Calendar;

/**
 * Created by HP USER on 03/05/2018.
 */

public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText txtDate;
    public DateDialog(View view){
        txtDate= (EditText)view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){
        final Calendar c = Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH);
        int days =c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this,year,month,days);
    }
         public  void onDateSet(DatePicker View,int year, int month, int days){
                String date= days+ "-" + month + "-" + year;
             txtDate.setText(date);
         }
}
