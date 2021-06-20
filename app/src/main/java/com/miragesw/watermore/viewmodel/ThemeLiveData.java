package com.miragesw.watermore.viewmodel;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ThemeLiveData {
    public static MutableLiveData<Integer> themes=new MutableLiveData<>();

    public LiveData<Integer> returnTheme(){
        return themes;
    }
    public void setThemes(int resId){
        themes.setValue(resId);
    }
}
