package com.example.tektek;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tektek.database.UserTable;
import com.example.tektek.utils.Calculations;
import com.example.tektek.utils.Constants;
import com.example.tektek.viewmodel.DbViewModel;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.OffsetDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.String.valueOf;


public class LauncherActivity extends AppCompatActivity {

    public void goMainScreen(){
        Intent intent = new Intent(this, MainPage.class);
        startActivity(intent);
    }


    UserTable user=new UserTable();
    UserTable lastUserRecord =new UserTable();
    Calculations calculations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        AndroidThreeTen.init(this);
        lastUserRecord.height=0;
        lastUserRecord.weight=0;
        lastUserRecord.dailyActivity=0;
        lastUserRecord.age=0;
        lastUserRecord.gender=0;
        lastUserRecord.temperature=0;

        DbViewModel dbViewModel=new DbViewModel(this.getApplication());
        calculations=new Calculations();

        //Radio Group
        RadioGroup radioGroup=findViewById(R.id.radiogroup);
        RadioButton radioButtonMale=findViewById(R.id.radio_male);
        RadioButton radioButtonFemale=findViewById(R.id.radio_female);


        radioGroup.setOnClickListener(this::onRadioButtonClicked);

        //spinner atama
        Spinner spinnerweight = findViewById(R.id.spinnerkg); //kilo
        Spinner spinnerheight = findViewById(R.id.spinnercm); //boy
        Spinner spinnerage = findViewById(R.id.spinneryr); //yaş
        Spinner spinnerDailyActivity = findViewById(R.id.spinnerda); //daily activity
        Spinner spinnerWeather = findViewById(R.id.spinnerwa); //weather
        ImageView imageView3 = findViewById(R.id.imageView3);
        ImageView burger1 = findViewById(R.id.burger);
        ImageView boykare = findViewById(R.id.imageView6);
        ImageView boycu = findViewById(R.id.boycu);
        ImageView yaskare = findViewById(R.id.imageView5);
        ImageView yasci = findViewById(R.id.calendar);
        Button save;


        //spinner veriler
        List<String> dailies = Arrays.asList(getResources().getStringArray(R.array.dailies));

        //spinnera array adapte
        ArrayAdapter<String> dailyActivtyArrayAdapter = new ArrayAdapter(this, R.layout.colorspinner, dailies);
        dailyActivtyArrayAdapter.setDropDownViewResource(R.layout.colorspinner_dropdown);
        spinnerDailyActivity.setAdapter(dailyActivtyArrayAdapter);
        spinnerDailyActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user.dailyActivity=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //laciverti
        List<String> weatheries = Arrays.asList(getResources().getStringArray(R.array.weatheries));

        ArrayAdapter<String> weatherArrayAdapter = new ArrayAdapter(this, R.layout.colorspinner, weatheries);
        weatherArrayAdapter.setDropDownViewResource(R.layout.colorspinner_dropdown);
        spinnerWeather.setAdapter(weatherArrayAdapter);

        spinnerWeather.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user.temperature=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //sayıları for dönügüsü ile vercem ondan mütevellit üçünü de int olarak tanımladım aralık için
        int kilo;
        int height;
        int yr;
        //anlamışsındır aralığı spinnera ciri
        List<String> kilos = new ArrayList<>();
        kilos.add("-");
        for(kilo = 27; kilo <= 170; kilo++) {
            String a = String.valueOf(kilo);
            kilos.add(a);
        }
        ArrayAdapter<String> weightArrayAdapter = new ArrayAdapter(this, R.layout.lilspinners, kilos);
        weightArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerweight.setAdapter(weightArrayAdapter);

        List<String> heights = new ArrayList<>();
        heights.add("-");
        for(height = 125; height <= 210; height++) {
            String a = String.valueOf(height);
            heights.add(a);
        }
        ArrayAdapter<String> heightArrayAdapter = new ArrayAdapter(this, R.layout.lilspinners, heights);
        heightArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerheight.setAdapter(heightArrayAdapter);

        List<String> years = new ArrayList<>();
        years.add("-");
        for(yr = 13; yr <= 70; yr++) {
            String a = String.valueOf(yr);
            years.add(a);
        }
        ArrayAdapter<String> ageArrayAdapter = new ArrayAdapter(this, R.layout.lilspinners, years);
        ageArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerage.setAdapter(ageArrayAdapter);
        //spinnerdan veri select edilince değişi
        spinnerweight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    imageView3.setImageResource(R.drawable.rectangle2after);
                    burger1.setImageResource(R.drawable.burger2);
                    Animation animation = AnimationUtils.loadAnimation(LauncherActivity.this,R.anim.fadein);
                    imageView3.startAnimation(animation);
                    user.weight=Integer.parseInt(parent.getSelectedItem().toString());


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerheight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    boykare.setImageResource(R.drawable.boyafter);
                    boycu.setImageResource(R.drawable.height1);
                    Animation animation = AnimationUtils.loadAnimation(LauncherActivity.this,R.anim.fadein);
                    boykare.startAnimation(animation);
                    user.height=Integer.parseInt(parent.getSelectedItem().toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position > 0) {
                    yaskare.setImageResource(R.drawable.ageafter);
                    yasci.setImageResource(R.drawable.calendar1);
                    Animation animation = AnimationUtils.loadAnimation(LauncherActivity.this,R.anim.fadein);
                    yaskare.startAnimation(animation);
                    user.age=Integer.parseInt(parent.getSelectedItem().toString());

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //button animasyonu
        save=(Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(LauncherActivity.this,R.anim.blink_anim);
                save.startAnimation(animation);
                boolean equalCondition=
                        user.age== lastUserRecord.age&&
                        user.gender== lastUserRecord.gender&&
                        user.temperature== lastUserRecord.temperature&&
                        user.dailyActivity== lastUserRecord.dailyActivity&&
                        user.weight== lastUserRecord.weight&&
                        user.height== lastUserRecord.height;


                if(!equalCondition){
                    //calculations.java
                    user.bmi=(int)calculations.bmi(user.weight,user.height);
                    user.idealminweight=calculations.idealMinWeight(user.height);
                    user.idealmaxweight=calculations.idealMaxWeight(user.height);
                    user.goal=calculations.goalWater(user.weight);
                    user.proteinmaxreq=calculations.dailymaxprotein(user.weight,user.bmi);
                    user.proteinminreq=calculations.dailyminprotein(user.weight,user.bmi);

                    dbViewModel.insertOne(user); //insert the record if its changed
                    //take added water field from last record (if there is a record)
                    //if there is no last record just insert
                    //bmi,protein and other stuff will be added to insertion because they are only dependent to this page
                }

                goMainScreen();
            }

        });



        dbViewModel.getLastRecord().observe(this,response->{

            if (response != null) {
                spinnerDailyActivity.setSelection(response.dailyActivity);
                spinnerWeather.setSelection(response.temperature);
                spinnerweight.setSelection(weightArrayAdapter.getPosition( Integer.toString(response.weight)));
                spinnerheight.setSelection(heightArrayAdapter.getPosition( Integer.toString(response.height)));
                spinnerage.setSelection(ageArrayAdapter.getPosition(Integer.toString(response.age)));
                if(response.date.getDayOfMonth()== OffsetDateTime.now().getDayOfMonth()){
                    user.drunk=response.drunk;
                }

                if(response.gender==Constants.GENDER_MALE){
                    radioButtonMale.setChecked(true);
                    user.gender=Constants.GENDER_MALE;
                }else{
                    radioButtonFemale.setChecked(true);
                    user.gender=Constants.GENDER_FEMALE;
                }
                lastUserRecord =response;

            }


        });


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_male:
                if (checked)
                    user.gender=Constants.GENDER_MALE;
                break;
            case R.id.radio_female:
                if (checked)
                    user.gender=Constants.GENDER_FEMALE;
                break;
        }
    }

}