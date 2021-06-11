package com.example.tektek;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        finish();
    }


    UserTable user=new UserTable();
    UserTable lastUserRecord =new UserTable();
    Calculations calculations;
    Boolean isLastRecordToday;
    AlertDialog.Builder adb;
    DbViewModel dbViewModel;
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
        isLastRecordToday=false; //THIS IS A TRIAL OF DEFAULT VALUE - REVIEW ALL THE CASES PLS

        user.height=0;
        user.weight=0;
        user.dailyActivity=0;
        user.age=0;
        user.gender=0;
        user.temperature=0;
        AndroidThreeTen.init(getApplication());
        user.date=OffsetDateTime.now();
        dbViewModel=new DbViewModel(this.getApplication());
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
        //CHECK IF NOTHING SELECTED AND GIVE USER WARNING TO SELECT
        //THERE IS NO NEED TO PUSH USER TO CHOOSE BTW BECAUSE I TOOK PRECAUTION AGAINST THE CASE THAT USER ENTRIES NOTHING
        save.setOnClickListener(v -> {
            Animation animation = AnimationUtils.loadAnimation(LauncherActivity.this,R.anim.blink_anim);
            save.startAnimation(animation);
            boolean equalCondition=
                    user.age== lastUserRecord.age&&
                    user.gender== lastUserRecord.gender&&
                    user.temperature== lastUserRecord.temperature&&
                    user.dailyActivity== lastUserRecord.dailyActivity&&
                    user.weight== lastUserRecord.weight&&
                    user.height== lastUserRecord.height;


            if(!equalCondition){//if there is a change

                user.bmi=(int)calculations.bmi(user.weight,user.height);
                user.idealminweight=calculations.idealMinWeight(user.height);
                user.idealmaxweight=calculations.idealMaxWeight(user.height);
                user.goal=calculations.goalWater(user.weight);
                user.proteinmaxreq=calculations.dailymaxprotein(user.weight,user.bmi);
                user.proteinminreq=calculations.dailyminprotein(user.weight,user.bmi);

                if(isThereUnselectedItem()){
                    showAlertDialogConditionOne();

                }else{
                    if(isLastRecordToday){
                        dbViewModel.replaceLastRecord(user);
                        //last record is today so update it
                    }
                    else{
                        dbViewModel.insertOne(user); //insert a new record if values changed and
                        //last record is not today
                    }
                    goMainScreen();
                }


                //take added water field from last record (if there is a record)
                //if there is no last record just insert
                //bmi,protein and other stuff will be added to insertion because they are only dependent to this page

            }else{//if there is no change in selections

                if(!isLastRecordToday){//ilk değişimsiz giriş buraya düşüyor
                    //Note: isLastRecordToday could be null and as a result activity will CRASH
                    //I could not assign a default value to lastUserRecord.date because it is OffSetDateTime type
                    // This case happens if getLastRecord (look below) response return null or
                    //the USER clicks save incredibly faster than observer which i do not know if it is possible
                    user.bmi=(int)calculations.bmi(user.weight,user.height); //we dont assign lastuserrecord to user because
                    user.idealminweight=calculations.idealMinWeight(user.height);   //first entry would give error
                    user.idealmaxweight=calculations.idealMaxWeight(user.height);
                    user.goal=calculations.goalWater(user.weight);
                    user.proteinmaxreq=calculations.dailymaxprotein(user.weight,user.bmi);
                    user.proteinminreq=calculations.dailyminprotein(user.weight,user.bmi);
                    if(isThereUnselectedItem()){
                        showAlertDialogConditionTwo();

                    }else{
                        dbViewModel.insertOne(user);
                        goMainScreen();
                    }


                }else{
                    if(isThereUnselectedItem()){
                        showAlertDialogConditionThree();
                    }else {
                        goMainScreen();
                    }

                }

                //this else statement is written for creating a new record in case: if there is a record in database and its date not today
                //take the old values, update the date(automatically) and insert it. This is done to track progress day by day in graphics
            }


        });



        dbViewModel.getLastRecord().observe(this,response->{
            //If there is a record in database and response return null for some reason
            // there is no code to take precaution against it right now
            if (response != null) {
                isLastRecordToday=response.date.getDayOfMonth()==OffsetDateTime.now().getDayOfMonth();
                spinnerDailyActivity.setSelection(response.dailyActivity);
                spinnerWeather.setSelection(response.temperature);
                spinnerweight.setSelection(weightArrayAdapter.getPosition( Integer.toString(response.weight)));
                spinnerheight.setSelection(heightArrayAdapter.getPosition( Integer.toString(response.height)));
                spinnerage.setSelection(ageArrayAdapter.getPosition(Integer.toString(response.age)));
                if(isLastRecordToday){
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

    public boolean isThereUnselectedItem(){
        return user.height == 0 || user.weight == 0 || user.age == 0 || user.gender == 0 ||
                user.temperature == 0 || user.dailyActivity == 0;
        }

        public void showAlertDialogConditionOne(){

                adb=new AlertDialog.Builder(this);
                adb.setTitle("Warning");
                adb.setMessage("Lack of selections cause miscalculations. Are you sure?");
                adb.setPositiveButton("Continue Anyway", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(isLastRecordToday){
                            dbViewModel.replaceLastRecord(user);
                            //last record is today so update it
                        }
                        else{
                            dbViewModel.insertOne(user); //insert a new record if values changed and
                            //last record is not today
                        }
                        goMainScreen();

                    }
                });
                adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                adb.show();

            }
    public void showAlertDialogConditionTwo(){

        adb=new AlertDialog.Builder(this);
        adb.setTitle("Warning");
        adb.setMessage("Lack of selections cause miscalculations. Are you sure?");
        adb.setPositiveButton("Continue Anyway", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dbViewModel.insertOne(user);
                goMainScreen();
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        adb.show();

    }
    public void showAlertDialogConditionThree(){

        adb=new AlertDialog.Builder(this);
        adb.setTitle("Warning");
        adb.setMessage("Lack of selections cause miscalculations. Are you sure?");
        adb.setPositiveButton("Continue Anyway", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goMainScreen();
            }
        });
        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
        adb.show();

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