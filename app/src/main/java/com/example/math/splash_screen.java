package com.example.math;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);
        int SPLASH_DISPLAY_LENGTH = 1000;
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(splash_screen.this, Login.class);
            splash_screen.this.startActivity(mainIntent);
            splash_screen.this.finish();
        }, SPLASH_DISPLAY_LENGTH);
    }
}