package com.trying.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        /*progressBar.getProgressDrawable().setColorFilter(
                Color.GRAY, android.graphics.PorterDuff.Mode.SRC_IN);*/
        progressBar.setBackgroundColor(Color.TRANSPARENT);
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 2000);
    }
}