package com.example.watermore.database;

import android.app.Application;

import androidx.lifecycle.LiveData;


import org.threeten.bp.OffsetDateTime;

import java.util.List;


public class LocalRepository {


    private Dao dao;
    private LiveData<String> date;
    private LiveData<List<String>> multipleRecordDates;
    private LiveData<UserTable> oneLastRecord;
    private LiveData<List<UserTable>> multipleRecords;


    public LocalRepository(Application application){
        AppDatabase db= AppDatabase.getDatabase(application);
        dao=db.Dao();
    }

    public void insertData(UserTable userTable){
    AppDatabase.databaseWriteExecutor.execute(()->{
        userTable.date= OffsetDateTime.now();
        dao.insertOne(userTable);
    });
    }

    public LiveData<String> getLastDate(){
        date =dao.getLastDate();
        return date;
    }

    public void replaceLastRecord(UserTable userTable){
        AppDatabase.databaseWriteExecutor.execute(()->{
            dao.delete();
            dao.insertOne(userTable);
        });
    }




    public LiveData<UserTable> getLastRecord(){
        oneLastRecord= dao.getLastRecord(); //ARTIK DATE'I DE dateString İLE TEK SEFERDE USERTABLE İLE ÇEKEBİLİRSİN NOT:IF RETURN ONE RECORD
                                //EĞER BIRDEN FAZLA VARSA FOR DÖNGÜSÜ İLE DATELERİ ALIP DATESTRİNGE ATARSIN
        //oneLastRecord.getValue().dateString=TiviTypeConverters.fromOffsetDateTime(dao.getLastRecord().getValue().date); //YEMEDİ AMK
        return oneLastRecord;
    }
    public LiveData<List<UserTable>> getLastSevenRecord(){
        multipleRecords=dao.getLastSevenRecord();
        return multipleRecords;
    }

    public LiveData<List<String>> getLastSevenRecordDate(){ //BUNA GEREK KALMADI LAAAAAAAAAAAAN
        multipleRecordDates=dao.getLastSevenRecordDate();
        return multipleRecordDates;
    }

    public void update(int drunk){
        AppDatabase.databaseWriteExecutor.execute(()->{
            dao.update(drunk);
        });

    }

}
