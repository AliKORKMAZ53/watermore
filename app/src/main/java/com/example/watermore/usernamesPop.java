package com.example.watermore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

public class usernamesPop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usernames_pop);
        //burası popup ölçü
        DisplayMetrics um = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(um);
        int width = um.widthPixels;
        int height = um.heightPixels;
        getWindow().setLayout((int) (width * .65), (int) (height * .25));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
    }
}