package com.example.tektek.viewmodel;

import android.app.Application;
import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.tektek.database.LocalRepository;
import com.example.tektek.database.UserTable;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.threeten.bp.OffsetDateTime;

import java.util.List;

public class DbViewModel extends ViewModel {
    private static LocalRepository localRepository;
    private LiveData<String> lastDate;
    private LiveData<UserTable> oneTable;
    private LiveData<List<UserTable>> multipleRecords;
    private LiveData<List<String>> multipleRecordsDate;


    public DbViewModel(Application application){
        this.localRepository=new LocalRepository(application);
        oneTable= localRepository.getLastRecord();
        lastDate= localRepository.getLastDate();
        multipleRecords=localRepository.getLastSevenRecord();
        multipleRecordsDate=localRepository.getLastSevenRecordDate();

    }


    public LiveData<List<UserTable>> getLastSevenRecords(){
        return multipleRecords;
    }
    public LiveData<List<String>> getLastSevenRecordsDate() {
        return multipleRecordsDate;
    }




    public LiveData<String> getLastDate(){
        return lastDate;
    }

    public LiveData<UserTable> getLastRecord(){
        return oneTable;
    }

    public void update(int drunk){
        localRepository.update(drunk);
    }

    public void insertOne(UserTable userTable){
        localRepository.insertData(userTable);
    }

}
