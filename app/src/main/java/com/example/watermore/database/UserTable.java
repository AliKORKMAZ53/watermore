package com.example.watermore.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.watermore.utils.TiviTypeConverters;

import org.threeten.bp.OffsetDateTime;


@Entity
public class UserTable {
   @PrimaryKey(autoGenerate = true)
    public int recordId;

   // @ColumnInfo(name= "user_name")
   // public String username;

    @ColumnInfo(name= "height")
    public int height;

    @ColumnInfo(name= "weight")
    public int weight;

    @ColumnInfo(name= "age")
    public int age;

    @ColumnInfo(name= "gender")
    public int gender;

    @ColumnInfo(name= "dailyActivity")
    public int dailyActivity;

    @ColumnInfo(name= "temperature")
    public int temperature;

    @ColumnInfo(name= "drunk")
    public int drunk;

    //below columns added to track the progress in graphics
    @ColumnInfo(name="goal")
    public double goal;

    @ColumnInfo(name ="bmi")
    public int bmi;

    @ColumnInfo(name ="idealminweight")
    public int idealminweight;

    @ColumnInfo(name ="idealmaxweight")
    public int idealmaxweight;

    @ColumnInfo(name = "proteinminreq")
    public int proteinminreq;

    @ColumnInfo(name = "proteinmaxreq")
    public int proteinmaxreq;



    @TypeConverters(TiviTypeConverters.class)
    @ColumnInfo(name = "date")
    public OffsetDateTime date;



}
