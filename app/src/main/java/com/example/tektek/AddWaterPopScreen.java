package com.example.tektek;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.tektek.database.UserTable;
import com.example.tektek.utils.TiviTypeConverters;
import com.example.tektek.viewmodel.DbViewModel;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.OffsetDateTime;

public class AddWaterPopScreen extends AppCompatActivity {
    int addwater;
    ImageView tea,tearec,teaolcu,glass,glassrec,glassolcu,cup,cuprec,cupolcu,pcup,pcuprec,pcupolcu,
            bottle,bottlerec,bottleolcu,surahi,surahirec,surahiolcu,musluk,muslukrec,binustu,verigir;
    TextView binustuyazi,binustuml;
    EditText binustuveri;

    DbViewModel dbViewModel;
    boolean isLastRecordToday;
    UserTable userTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water_pop);
        userTable=new UserTable();
        //Database and time stuff
        AndroidThreeTen.init(this);
        dbViewModel=new DbViewModel(getApplication());

        dbViewModel.getLastRecord().observe(this,response->{
            if(response!=null){
                userTable=response;
                isLastRecordToday= OffsetDateTime.now().getDayOfMonth()==
                        (response.date).getDayOfMonth(); //GİVES NULL

            }else {
                Log.d("lastdate","lastdate response is empty");
            }
        });



        //burası popup ölçü
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x=0;
        params.y=-20;
        //her içilen mL için 3 eleman var onları tanımladım
        getWindow().setAttributes(params);
        tea = findViewById(R.id.tea);
        tearec = findViewById(R.id.tearec);
        teaolcu = findViewById(R.id.teaolcu);
        glass = findViewById(R.id.glass);
        glassrec = findViewById(R.id.glassrec);
        glassolcu = findViewById(R.id.glassolcu);
        cup = findViewById(R.id.cupimage);
        cuprec = findViewById(R.id.cuprec);
        cupolcu = findViewById(R.id.cupolcu);
        pcup = findViewById(R.id.karton);
        pcuprec = findViewById(R.id.kartonrec);
        pcupolcu = findViewById(R.id.papercupolcu);
        bottle = findViewById(R.id.sise);
        bottlerec = findViewById(R.id.siserec);
        bottleolcu = findViewById(R.id.bottleolcu);
        surahi = findViewById(R.id.surahi);
        surahirec = findViewById(R.id.surahirec);
        surahiolcu =findViewById(R.id.surahiolcu);
        musluk = findViewById(R.id.musluk);
        muslukrec = findViewById(R.id.muslukrec);
        binustu = findViewById(R.id.binustu);
        verigir = findViewById(R.id.binustugir);
        binustuyazi = findViewById(R.id.binustuyazi);
        binustuveri = findViewById(R.id.binustuedit);
        binustuml = findViewById(R.id.binustuml);
        verigir.setVisibility(View.INVISIBLE);
        binustuml.setVisibility(View.INVISIBLE);
        binustuveri.setVisibility(View.INVISIBLE);
        binustuyazi.setVisibility(View.INVISIBLE);

        binustuveri.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            addwater=Integer.parseInt(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void binustuvisib(){
        verigir.setVisibility(View.INVISIBLE);
        binustuml.setVisibility(View.INVISIBLE);
        binustuveri.setVisibility(View.INVISIBLE);
        binustuyazi.setVisibility(View.INVISIBLE);
    }
    public void setFirstver(){
        tea.setImageResource(R.drawable.tea);
        teaolcu.setImageResource(R.drawable.teaolcu);
        tearec.setImageResource(R.color.transparent);
        glass.setImageResource(R.drawable.waterglass);
        glassolcu.setImageResource(R.drawable.bardakolcu);
        glassrec.setImageResource(R.color.transparent);
        cup.setImageResource(R.drawable.cup);
        cupolcu.setImageResource(R.drawable.cupolcu);
        cuprec.setImageResource(R.color.transparent);
        pcup.setImageResource(R.drawable.papercup);
        pcupolcu.setImageResource(R.drawable.kartonolcu);
        pcuprec.setImageResource(R.color.transparent);
        bottle.setImageResource(R.drawable.bottle);
        bottleolcu.setImageResource(R.drawable.bottleolcu);
        bottlerec.setImageResource(R.color.transparent);
        surahi.setImageResource(R.drawable.surahi);
        surahiolcu.setImageResource(R.drawable.surahiolcu);
        surahirec.setImageResource(R.color.transparent);
        musluk.setImageResource(R.drawable.musluk);
        binustu.setImageResource(R.drawable.binustu);
        muslukrec.setImageResource(R.color.transparent);
    }

    public void OnClick(View v){
        switch (v.getId()){
            case R.id.tearec :

                setFirstver();
                addwater=100;
                Animation fade = AnimationUtils.loadAnimation(AddWaterPopScreen.this,R.anim.fadein);
                tea.setImageResource(R.drawable.teaafter);
                tea.startAnimation(fade);
                teaolcu.setImageResource(R.drawable.teaolcuafter);
                teaolcu.startAnimation(fade);
                tearec.setImageResource(R.drawable.addwaterselectedblue);
                tearec.startAnimation(fade);

                binustuvisib();
                break;

            case R.id.glassrec:
                setFirstver();
                addwater=200;
                Animation fade2 = AnimationUtils.loadAnimation(AddWaterPopScreen.this,R.anim.fadein);
                glass.setImageResource(R.drawable.glassafter);
                glass.startAnimation(fade2);
                glassolcu.setImageResource(R.drawable.glassolcuafter);
                glassolcu.startAnimation(fade2);
                glassrec.setImageResource(R.drawable.addwaterselectedred);
                glassrec.startAnimation(fade2);
                binustuvisib();
                break;

            case R.id.cuprec :
                setFirstver();
                addwater=300;
                Animation fade3 = AnimationUtils.loadAnimation(AddWaterPopScreen.this,R.anim.fadein);
                cup.setImageResource(R.drawable.cupafter);
                cup.startAnimation(fade3);
                cupolcu.setImageResource(R.drawable.cupolcuafter);
                cupolcu.startAnimation(fade3);
                cuprec.setImageResource(R.drawable.addwaterselectedblue);
                cuprec.startAnimation(fade3);
                binustuvisib();
                break;

            case R.id.kartonrec :
                setFirstver();
                addwater=400;
                Animation fade4 = AnimationUtils.loadAnimation(AddWaterPopScreen.this,R.anim.fadein);
                pcup.setImageResource(R.drawable.papercupafter);
                pcup.startAnimation(fade4);
                pcupolcu.setImageResource(R.drawable.paperolcuafter);
                pcupolcu.startAnimation(fade4);
                pcuprec.setImageResource(R.drawable.addwaterselectedred);
                pcuprec.startAnimation(fade4);
                binustuvisib();
                break;

            case R.id.siserec :
                setFirstver();
                addwater=500;
                Animation fade5 = AnimationUtils.loadAnimation(AddWaterPopScreen.this,R.anim.fadein);
                bottle.setImageResource(R.drawable.bottleafter);
                bottle.startAnimation(fade5);
                bottleolcu.setImageResource(R.drawable.bottleolcuafter);
                bottleolcu.startAnimation(fade5);
                bottlerec.setImageResource(R.drawable.addwaterselectedblue);
                bottlerec.startAnimation(fade5);
                binustuvisib();
                break;

            case R.id.surahirec :
                setFirstver();
                addwater=1000;
                Animation fade6 = AnimationUtils.loadAnimation(AddWaterPopScreen.this,R.anim.fadein);
                surahi.setImageResource(R.drawable.surahiafter);
                surahi.startAnimation(fade6);
                surahiolcu.setImageResource(R.drawable.surahiolcuafter);
                surahiolcu.startAnimation(fade6);
                surahirec.setImageResource(R.drawable.addwaterselectedred);
                surahirec.startAnimation(fade6);
                binustuvisib();
                break;

            case R.id.muslukrec :
                setFirstver();
                Animation fade7 = AnimationUtils.loadAnimation(AddWaterPopScreen.this,R.anim.fadein);
                musluk.setImageResource(R.drawable.muslukafter);
                musluk.startAnimation(fade7);
                binustu.setImageResource(R.drawable.binustuafter);
                binustu.startAnimation(fade7);
                muslukrec.setImageResource(R.drawable.addwaterselectedblue);
                muslukrec.startAnimation(fade7);
                verigir.setVisibility(View.VISIBLE);
                binustuml.setVisibility(View.VISIBLE);
                binustuveri.setVisibility(View.VISIBLE);
                binustuyazi.setVisibility(View.VISIBLE);
                break;
        }
    }


    //check the date, if the last record is today
    //then add water, otherwise create new record with last values (except the drunk, drunk should be zeroed) then add water
    public void onSavePortionClicked(View view) throws NullPointerException{
        if(isLastRecordToday){
            dbViewModel.update(addwater);
        }else{
            userTable.drunk=addwater;
            dbViewModel.insertOne(userTable);
            //new record inserting doesnt work
        }
        AddWaterPopScreen.this.finish();
    }

}