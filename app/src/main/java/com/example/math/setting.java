package com.example.math;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class setting extends Fragment {

    public setting() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    /**
     * SwitchCompat switchCompat;
     * SharedPreferences sharedPreferences = null;
     */


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /**ImageView sun_moon = view.findViewById(R.id.sun_moon);
         switchCompat = view.findViewById(R.id.DarkLight);
         sharedPreferences = requireContext().getSharedPreferences("night", 0);
         boolean booleanValue = sharedPreferences.getBoolean("night_mode", true);
         if (booleanValue) {
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
         switchCompat.setChecked(true);
         sun_moon.setImageResource(R.drawable.dark_mode_24);
         }

         switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
         if (isChecked) {
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
         switchCompat.setChecked(true);
         sun_moon.setImageResource(R.drawable.dark_mode_24);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putBoolean("Dark Mode ", true);
         editor.apply();
         } else {
         AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
         switchCompat.setChecked(false);
         sun_moon.setImageResource(R.drawable.dark_mode_24);
         SharedPreferences.Editor editor = sharedPreferences.edit();
         editor.putBoolean("Dark Mode ", false);
         editor.apply();
         }
         });*/
    }
}