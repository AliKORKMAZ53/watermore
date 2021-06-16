package com.example.watermore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.watermore.viewmodel.DbViewModel;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenSplashActivity extends AppCompatActivity {

    DbViewModel dbViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_splash);
        dbViewModel=new DbViewModel(getApplication());
        Intent intentMain=new Intent(this,MainPage.class);
        Intent usernamesActivity=new Intent(this,usernamesPop.class);
        Handler handler=new Handler();

        dbViewModel.getLastRecord().observe(this,response->{
            handler.postDelayed(() -> {
                if(response==null){
                    startActivity(usernamesActivity);
                }else{
                    startActivity(intentMain);
                }
                finish();
            },2000);
        });

    }







}