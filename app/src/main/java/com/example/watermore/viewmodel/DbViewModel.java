package com.example.watermore.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.watermore.database.LocalRepository;
import com.example.watermore.database.UserTable;

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
    public void replaceLastRecord(UserTable userTable){
        localRepository.replaceLastRecord(userTable);
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
