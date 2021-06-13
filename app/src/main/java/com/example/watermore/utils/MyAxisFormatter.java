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
        }
    }


