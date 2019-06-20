package com.example.jinha.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SettingSystemActivity extends AppCompatActivity {

    Switch swAutoLogin;
    String id;
    String pw;
    TextView tv;
    SharedPreferences setting;
    SharedPreferences.Editor editor;
    @SuppressLint("CommitPrefEdits")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settingsystem);
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
        pw = intent.getStringExtra("PW");
        setting = getSharedPreferences("setting",0);
        editor = setting.edit();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);



        swAutoLogin = (Switch)findViewById(R.id.swAutoLogin); // 자동로그인
        if(pref.getBoolean("Auto_Login_enabled", Boolean.parseBoolean(""))){
            swAutoLogin.setChecked(true);
        };
        swAutoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    editor.putString("ID", id);
                    editor.putString("PW", pw);
                    editor.putBoolean("Auto_Login_enabled",true);
                    editor.apply();
                } else {
                    // The toggle is disabled
                    editor.remove("ID");
                    editor.remove("PW");
                    editor.remove("Auto_Login_enabled");
                    editor.clear();
                    editor.commit();
                }
            }
        });

    }
}