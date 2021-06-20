package com.miragesw.watermore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.miragesw.watermore.viewmodel.ThemeLiveData;

public class settings extends AppCompatActivity {

    public void goMainScreen(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }

    public void openAboutUsPOP(){
        Intent intent = new Intent(this, AboutUs.class);
        startActivity(intent);
    }

    public void openTermsPOP(){
        Intent intent = new Intent(this, termsPop.class);
        startActivity(intent);
    }
    Button aboutUs;
    Button terms;
    ImageButton geritusu;
    ImageView tema1,tema2,tema3,tema4,tema5,tema6;
    ConstraintLayout constraintLayout;
    ThemeLiveData themeLiveData=new ThemeLiveData();
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences=getSharedPreferences("themes",Context.MODE_PRIVATE);
        constraintLayout=findViewById(R.id.settings_layout);
        constraintLayout.setBackgroundResource(sharedPreferences.getInt("applyTheme",R.drawable.backgg));
        themeLiveData.returnTheme().observe(this,response->{
                constraintLayout.setBackgroundResource(response);
        });
        editor=sharedPreferences.edit();

        tema1=findViewById(R.id.tema1);
        tema2=findViewById(R.id.tema2);
        tema3=findViewById(R.id.tema3);
        tema4=findViewById(R.id.tema4);
        tema5=findViewById(R.id.tema5);
        tema6=findViewById(R.id.tema6);
        geritusu=findViewById(R.id.geritusu);
        aboutUs=findViewById(R.id.aboutus);
        terms=findViewById(R.id.terms);


        geritusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMainScreen();
            }
        });
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutUsPOP();
            }
        });
        terms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTermsPOP();
            }
        });

    }

    public void setFirstVer(){
        tema1.setImageResource(R.drawable.pinkiecircle);
        tema2.setImageResource(R.drawable.maincircle);
        tema3.setImageResource(R.drawable.darkwatercircle);
        tema4.setImageResource(R.drawable.leafcircle);
        tema5.setImageResource(R.drawable.oceancircle);
        tema6.setImageResource(R.drawable.volcanocircle);
    }

    public void OnClick(View v){
        switch(v.getId()){
            case R.id.tema1:
                setFirstVer();
                themeLiveData.setThemes(R.drawable.pinkie);
                editor.putInt("applyTheme",R.drawable.pinkie);
                editor.apply();
                tema1.setImageResource(R.drawable.pinkiecircleselected);
                Animation animation = AnimationUtils.loadAnimation(settings.this,R.anim.fadein);
                tema1.startAnimation(animation);
                break;

            case R.id.tema2:
                setFirstVer();
                themeLiveData.setThemes(R.drawable.backgg);
                editor.putInt("applyTheme",R.drawable.backgg);
                editor.apply();
                tema2.setImageResource(R.drawable.maincircleselected);
                Animation animation2 = AnimationUtils.loadAnimation(settings.this,R.anim.fadein);
                tema2.startAnimation(animation2);
                break;

            case R.id.tema3:
                setFirstVer();
                themeLiveData.setThemes(R.drawable.darkwater);
                editor.putInt("applyTheme",R.drawable.darkwater);
                editor.apply();
                tema3.setImageResource(R.drawable.darkwatercircleselected);
                Animation animation3 = AnimationUtils.loadAnimation(settings.this,R.anim.fadein);
                tema3.startAnimation(animation3);
                break;

            case R.id.tema4:
                setFirstVer();
                themeLiveData.setThemes(R.drawable.leaf);
                editor.putInt("applyTheme",R.drawable.leaf);
                editor.apply();
                tema4.setImageResource(R.drawable.leafcircleselected);
                Animation animation4 = AnimationUtils.loadAnimation(settings.this,R.anim.fadein);
                tema4.startAnimation(animation4);
                break;

            case R.id.tema5:
                setFirstVer();
                themeLiveData.setThemes(R.drawable.ocean);
                editor.putInt("applyTheme",R.drawable.ocean);
                editor.apply();
                tema5.setImageResource(R.drawable.oceancircleselected);
                Animation animation5 = AnimationUtils.loadAnimation(settings.this,R.anim.fadein);
                tema5.startAnimation(animation5);
                break;

            case R.id.tema6:
                setFirstVer();
                themeLiveData.setThemes(R.drawable.volcano);
                editor.putInt("applyTheme",R.drawable.volcano);
                editor.apply();
                tema6.setImageResource(R.drawable.volcanocircleselected);
                Animation animation6 = AnimationUtils.loadAnimation(settings.this,R.anim.fadein);
                tema6.startAnimation(animation6);
                break;

        }
    }

}