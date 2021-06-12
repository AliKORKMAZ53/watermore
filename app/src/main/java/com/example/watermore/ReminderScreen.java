package com.example.watermore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class ReminderScreen extends AppCompatActivity {

    public void returnMainPage(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }
    ImageView geritusu,sabah,ogle,ikindi,gunbatimi,aksam,gece;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_screen);


        aksam=findViewById(R.id.aksamalarm);
        geritusu=findViewById(R.id.geritusu);
        sabah = findViewById(R.id.sabahalarm);
        ogle=findViewById(R.id.oglealarm);
        ikindi=findViewById(R.id.ikindialarm);
        gunbatimi=findViewById(R.id.gunbatimialarm);
        gece=findViewById(R.id.gecealarm);

        geritusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            returnMainPage();
            }
        });


        sabah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sabah.setImageResource(R.drawable.sabahafter);
                Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
                sabah.startAnimation(animation1);
            }
        });


        ogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ogle.setImageResource(R.drawable.ogleafter);
                Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
                ogle.startAnimation(animation1);
            }
        });


        ikindi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                Toast.makeText(getApplicationContext(),v.getBackground().toString(),Toast.LENGTH_LONG).show();
                ikindi.setImageResource(R.drawable.ikindiafter);
                Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
                ikindi.startAnimation(animation1);
                }
        });


        gunbatimi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gunbatimi.setImageResource(R.drawable.duskafter);
                Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
                gunbatimi.startAnimation(animation1);
            }
        });


        aksam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aksam.setImageResource(R.drawable.aksamafter);
                Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
                aksam.startAnimation(animation1);
            }
        });


        gece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gece.setImageResource(R.drawable.geceafter);
                Animation animation1 = AnimationUtils.loadAnimation(ReminderScreen.this,R.anim.fadein);
                gece.startAnimation(animation1);
            }
        });
    }
}