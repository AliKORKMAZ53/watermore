package com.miragesw.watermore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miragesw.watermore.adapter.SlideAdapter;

public class SliderActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private SlideAdapter myadapter;
    Intent usernamepopIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
        usernamepopIntent=new Intent(this,usernamesPop.class);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapter(getApplication());
        viewPager.setAdapter(myadapter);

        myadapter.checkButtonClicked().observe(this,response->{
            if(response==true){
                startActivity(usernamepopIntent);
            }
        });

    }

}