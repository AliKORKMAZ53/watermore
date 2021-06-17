package com.miragesw.watermore.utils;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;


public class MyAxisFormatter extends ValueFormatter {
SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String getFormattedValue(float value) {
        String dateString= formatter.format(value);
        return dateString;
        }
    }


