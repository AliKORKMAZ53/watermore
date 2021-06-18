package com.miragesw.watermore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miragesw.watermore.utils.Constants;
import com.miragesw.watermore.viewmodel.DbViewModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.OffsetDateTime;

import pl.droidsonroids.gif.GifImageView;

public class MainPage extends AppCompatActivity {

    //boy-kilo girmeye geri gönderiyo
    public void goUpdateValues(){
        Intent intent = new Intent(this, LauncherActivity.class);
        startActivity(intent);
    }
    //su eklemeye gönderiyo
    public void addWaterScreen(){
        Intent intent = new Intent(this, AddWaterPopScreen.class);
        startActivity(intent);
    }
    public void showHealthScreen(){
        Intent intent = new Intent(this, HealthStats.class);
        startActivity(intent);
    }
    public void goGraphicScreen(){
        Intent intent=new Intent(this,GraphicActivity.class);
        intent.putExtra("age",age);
        startActivity(intent);
    }
    public void setReminderScreen(){
        Intent intent = new Intent(this, ReminderScreen.class);
        startActivity(intent);
    }

    TextView bmiText;
    TextView goalText;
    TextView droppercentage;
    Button updater;
    Button waterAdder;
    Button degerekran;
    Button setReminder;
    int age;
    GifImageView damlagif;
    TextView usernameTextView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        sharedPreferences=getSharedPreferences("routing", Context.MODE_PRIVATE);

        //ADS
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        AdView adView=findViewById(R.id.adView);
        AdRequest adRequest=new AdRequest.Builder()
                .build();
        adView.loadAd(adRequest);
        //ADS END

        usernameTextView=findViewById(R.id.textView11);
        usernameTextView.setText(sharedPreferences.getString("usernameKey","Kullanıcı"));


        damlagif =findViewById(R.id.damla);
        Button graphicshowbutton=findViewById(R.id.graphicShowButton);
        AndroidThreeTen.init(getApplication());
        bmiText=findViewById(R.id.bmitextmainpage);
        goalText=findViewById(R.id.goalwatertext);
        droppercentage=findViewById(R.id.droppercentage);

        DbViewModel dbViewModel=new DbViewModel(this.getApplication());
      /*  dbViewModel.getLastDate().observe(this, response->{
            if(response!=null){
                isLastRecordToday= OffsetDateTime.now().getDayOfMonth()==
                        TiviTypeConverters.toOffsetDateTime(dbViewModel.getLastDate().getValue()).getDayOfMonth();

            }

        });*/
        ImageView gender=findViewById(R.id.imageView13); //imageview13 -> gender view
        dbViewModel.getLastRecord().observe(this,response->{

          if(response!=null) {
              age=response.age;
              String trim = String.valueOf(((double) response.drunk / 1000) / response.goal);
              int stringsize = trim.length();
              if(response.date.getDayOfYear()==OffsetDateTime.now().getDayOfYear()){

              if (stringsize > 3) {
                  trim = trim.substring(2, 4);
              } else if (stringsize == 3) {
                  trim = trim.substring(2, 3);  //KÜÇÜK DEĞERLERDE HALA SORUN VAR %09 GÖRÜNDÜ AQ
              } else {
                  trim = String.valueOf(0);
              }
              if (((double) response.drunk / 1000) >= response.goal) {
                  droppercentage.setText("%100");
                  damlagif.setBackgroundResource(R.drawable.gif100);
              } else {
                  droppercentage.setText("%" + trim);
                  if(Integer.valueOf(trim)>0&&Integer.valueOf(trim)<21){
                      damlagif.setBackgroundResource(R.drawable.gif00);
                  }else if(Integer.valueOf(trim)>20&&Integer.valueOf(trim)<41){
                      damlagif.setBackgroundResource(R.drawable.gif20);
                  }else if(Integer.valueOf(trim)>40&&Integer.valueOf(trim)<61){
                      damlagif.setBackgroundResource(R.drawable.gif40);
                  }else if(Integer.valueOf(trim)>60&&Integer.valueOf(trim)<81){
                      damlagif.setBackgroundResource(R.drawable.gif60);
                  }else if(Integer.valueOf(trim)>80&&Integer.valueOf(trim)<100){
                      damlagif.setBackgroundResource(R.drawable.gif80);
                  }
              }
          }else{
                  droppercentage.setText("%0");
                  damlagif.setBackgroundResource(R.drawable.gif00);
              }
              bmiText.setText(String.valueOf(response.bmi)+" "+ getResources().getString(R.string.bmishort));
              goalText.setText(String.valueOf(String.format("%.2f", response.goal))+" L");
              if(response.gender== Constants.GENDER_MALE){
                  gender.setBackgroundResource(R.drawable.man);
              }else if(response.gender==Constants.GENDER_FEMALE){
                  gender.setBackgroundResource(R.drawable.woman);
              }else{
                  Toast.makeText(this,"Cinsiyet belirtilmedi!",Toast.LENGTH_LONG).show();
              }
          }

        });



        graphicshowbutton.setOnClickListener(view->{
            goGraphicScreen();
        });

        waterAdder=findViewById(R.id.addwaterbutton);
        waterAdder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWaterScreen();
            }
        });

        updater = findViewById(R.id.updatevalues);
        updater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goUpdateValues();
            }
        });

        degerekran = findViewById(R.id.degers);
        degerekran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHealthScreen();
            }
        });

        setReminder = findViewById(R.id.reminder);
        setReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setReminderScreen();
            }
        });
    }
}