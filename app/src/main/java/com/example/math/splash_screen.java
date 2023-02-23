package com.example.math;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class splash_screen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);
        int SPLASH_DISPLAY_LENGTH = 1000;
        //handler of the splash icon
        new Handler().postDelayed(() -> {
            SharedPreferences sharedPreferences= getSharedPreferences(Login.PREFS_NAME,0);
            boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
            Intent mainIntent;
            //if hasLoggedIn  is a boolean variable return if the user logged in or not
            if(hasLoggedIn){
                //if true open the Main activity inside the app
                mainIntent = new Intent(splash_screen.this, MainActivity.class);
            }else {
                //else open the Login screen
                mainIntent = new Intent(splash_screen.this, Login.class);
            }
            splash_screen.this.startActivity(mainIntent);
            splash_screen.this.finish();
        // Handlin time of the splach screen
        }, SPLASH_DISPLAY_LENGTH);
    }
}