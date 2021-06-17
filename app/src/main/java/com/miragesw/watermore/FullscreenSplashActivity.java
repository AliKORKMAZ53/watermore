package com.miragesw.watermore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.miragesw.watermore.viewmodel.DbViewModel;


public class FullscreenSplashActivity extends AppCompatActivity {


    SharedPreferences sharedPreferences;
    DbViewModel dbViewModel;
    String username;
    //int sliderCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_splash);

        Intent sliderActIntent=new Intent(this,SliderActivity.class);
        Intent intentMain=new Intent(this,MainPage.class);
        //Intent usernamepopIntent=new Intent(this,usernamesPop.class);

        sharedPreferences=getSharedPreferences("username", Context.MODE_PRIVATE);
        username=sharedPreferences.getString("usernameKey","null");
        //sliderCount=sharedPreferences.getInt("sliderCountKey",0);//slider will count this up +1 -not needed


        dbViewModel=new DbViewModel(getApplication());

        Handler handler=new Handler();

        dbViewModel.getLastRecord().observe(this,response->{//FİRST HANDLE THE SHAREDPREFERENCE
            handler.postDelayed(() -> {
                if(response==null){
                    if(username==null||username=="null"){
                        startActivity(sliderActIntent);

                    }else{
                        startActivity(intentMain);
                    }
                }else{
                    startActivity(intentMain);
                }
                finish();
            },2000);
        });

    }








}