package com.miragesw.watermore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class usernamesPop extends AppCompatActivity {

    EditText usernameEdit;
    SharedPreferences sharedPreferences;
    Button btnSave;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent mainPageIntent=new Intent(this,MainPage.class);
        Intent LauncherActIntent=new Intent(this,LauncherActivity.class);
        sharedPreferences=getSharedPreferences("routing", Context.MODE_PRIVATE);
        setContentView(R.layout.activity_usernames_pop);


        usernameEdit=findViewById(R.id.enterusername);
        btnSave=findViewById(R.id.saveusername);
        username=sharedPreferences.getString("usernameKey","null");
        if(username=="null"||username==null){

        }else{
            startActivity(mainPageIntent);
            finish();
        }


        usernameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            username=s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSave.setOnClickListener(v -> {
            if(username.length()>14){
                Toast.makeText(this,R.string.characterLimit,Toast.LENGTH_LONG).show();
            }else if(username.length()==0){
                Toast.makeText(this,R.string.characterEmpty,Toast.LENGTH_LONG).show();
            }
            else{
                SharedPreferences.Editor usernameKey = sharedPreferences.edit().putString("usernameKey", username);
                usernameKey.apply();
                startActivity(LauncherActIntent);
                finish();
            }

        });

    }
}