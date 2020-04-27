package com.allisonkosy.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(()-> {
//        .
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            boolean bool = preferences.getBoolean("theme", false);

            if(bool){

                //Toast.makeText(this, "There is", Toast.LENGTH_LONG).show();
//                setTheme();
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            goToMaun();

        },2000);
    }

    private void goToMaun(){
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class);

        startActivity(intent);
        finish();
    }
}
