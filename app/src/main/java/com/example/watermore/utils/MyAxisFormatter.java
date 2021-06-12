package com.example.watermore.utils;

import android.text.format.DateUtils;
import android.util.Log;

import com.github.mikephil.charting.formatter.ValueFormatter;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.OffsetDateTime;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MyAxisFormatter extends ValueFormatter {
SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String getFormattedValue(float value) {
        String dateString= formatter.format(value);
        return dateString;
     /*   if (value>999){
            return String.valueOf(Math.floor(value)).substring(2)+"/"+String.valueOf(value).substring(0,2);
        }else{
            return String.valueOf(Math.floor(value)).substring(1)+"/"+String.valueOf(value).substring(0,1);
        }
*/
        /*
        if (value>999){
            return String.valueOf(Math.floor(value)).substring(2).replace(".0","")+"/"+String.valueOf(value).substring(0,2);
        }else{
            if(value%100>31){
                return "0";
            }else{
                if(String.valueOf(value).charAt(1)=='0'){
                    return String.valueOf((int)value).substring(2).replace(".0","")+"/"+String.valueOf(value).substring(0,1);
                }else{
                    return String.valueOf((int)value).substring(1).replace(".0","")+"/"+String.valueOf(value).substring(0,1);
                }
            }
*/


        }
    }


