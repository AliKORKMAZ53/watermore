package com.example.tektek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tektek.utils.Calculations;
import com.example.tektek.viewmodel.DbViewModel;

public class HealthStats extends AppCompatActivity {

    public void turnMainScreen(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
        finish();
    }
    Calculations calculations=new Calculations();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthstats);

        DbViewModel dbViewModel=new DbViewModel(getApplication());

        Button geri =(Button) findViewById(R.id.back);
        ImageView ana = findViewById(R.id.anaresim);
        ImageView i1 = findViewById(R.id.i1);
        ImageView i2 = findViewById(R.id.i2);
        ImageView i3 = findViewById(R.id.i3);
        ImageView i4 = findViewById(R.id.i4);
        ImageView i5 = findViewById(R.id.i5);
        ImageView i6 = findViewById(R.id.i6);
        ImageView i7 = findViewById(R.id.i7);
        ImageView baslikres = findViewById(R.id.imageView7);
        ImageView degerarka = findViewById(R.id.degerarka);
        TextView t1 = findViewById(R.id.baslik);
        TextView t2 = findViewById(R.id.vki);
        TextView t3 = findViewById(R.id.ik);
        TextView t4 = findViewById(R.id.vkm);
        TextView t5 = findViewById(R.id.bmh);
        TextView t6 = findViewById(R.id.gpi);
        TextView t7 = findViewById(R.id.gsi);
        TextView t8 = findViewById(R.id.gyi);

        TextView d1 = findViewById(R.id.bmitext);
        TextView d2 = findViewById(R.id.idealweighttext);
        TextView d3 = findViewById(R.id.bloodratetext);
        TextView d4 = findViewById(R.id.basalmetatext);
        TextView d5 = findViewById(R.id.dailyproteintext);
        TextView d6 = findViewById(R.id.dailywatertext);
        TextView d7 = findViewById(R.id.dailyfattext);


        dbViewModel.getLastRecord().observe(this,response->{
            if(response!=null){
                d1.setText(String.valueOf(response.bmi)+" "+ getResources().getString(R.string.bmishort));
                d2.setText(String.valueOf(response.idealminweight)+"-"+String.valueOf(response.idealmaxweight)+" kg");
                d3.setText(String.valueOf(calculations.bloodRate(response.gender,response.weight))+" mL");
                int bmh=calculations.bmh(response.gender,response.weight,response.height,response.age);
                d4.setText(String.valueOf(bmh)+" kcal");
                d5.setText(String.valueOf(response.proteinminreq)+"-"+String.valueOf(response.proteinmaxreq)+" gr");
                d6.setText(String.valueOf(String.format("%.2f", response.goal))+" L");
                d7.setText(String.valueOf(calculations.minFat(bmh))+"-"+String.valueOf(calculations.maxFat(bmh))+" gr");

            }
            });

        Animation animation1 = AnimationUtils.loadAnimation(HealthStats.this,R.anim.fadein);
        Animation animation2 = AnimationUtils.loadAnimation(HealthStats.this,R.anim.lefttoright);
        Animation animation3 = AnimationUtils.loadAnimation(HealthStats.this,R.anim.bounce);
        ana.startAnimation(animation1);
        degerarka.startAnimation(animation1);
        baslikres.startAnimation(animation1);
        t1.startAnimation(animation1);
        t2.startAnimation(animation2);
        t3.startAnimation(animation2);
        t4.startAnimation(animation2);
        t5.startAnimation(animation2);
        t6.startAnimation(animation2);
        t7.startAnimation(animation2);
        t8.startAnimation(animation2);
        d1.startAnimation(animation1);
        d2.startAnimation(animation1);
        d3.startAnimation(animation1);
        d4.startAnimation(animation1);
        d5.startAnimation(animation1);
        d6.startAnimation(animation1);
        d7.startAnimation(animation1);
        i1.startAnimation(animation3);
        i2.startAnimation(animation3);
        i3.startAnimation(animation3);
        i4.startAnimation(animation3);
        i5.startAnimation(animation3);
        i6.startAnimation(animation3);
        i7.startAnimation(animation3);
        geri.startAnimation(animation1);
        geri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnMainScreen();
            }
        });
    }
}