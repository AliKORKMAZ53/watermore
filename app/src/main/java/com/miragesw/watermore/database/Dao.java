package com.miragesw.watermore.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {


    @Query("SELECT date FROM UserTable ORDER BY date DESC LIMIT 1")
    LiveData<String> getLastDate();

    @Query("SELECT * FROM UserTable ORDER BY date DESC LIMIT 1")
    LiveData<UserTable> getLastRecord();

    @Query("SELECT * FROM UserTable ORDER BY date DESC LIMIT 8") //for graphics
    LiveData<List<UserTable>> getLastSevenRecord();

    @Query("SELECT date FROM UserTable ORDER BY date DESC LIMIT 8")
    LiveData<List<String>> getLastSevenRecordDate();//not in use


    @Query("UPDATE UserTable SET drunk = :drunk + drunk WHERE recordId=(SELECT Max(recordId) FROM UserTable)")
    void update(int drunk);

    @Query("DELETE FROM UserTable WHERE recordId=(SELECT MAX(recordId) FROM UserTable)")
    void delete();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(UserTable userTable);
}
