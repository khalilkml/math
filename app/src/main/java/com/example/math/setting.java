package com.example.math;


import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class setting extends Fragment {

    public setting() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    ImageView sun_moon;
    androidx.appcompat.widget.SwitchCompat switchCompat;
    SharedPreferences sharedPreferences;

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        sun_moon = view.findViewById(R.id.sun_moon);
        switchCompat = view.findViewById(R.id.DarkLight);
        sharedPreferences = requireContext().getSharedPreferences("night", 0);

        // Initialize the switch state based on the system's dark mode setting
        int systemNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        boolean isNightModeEnabled = (systemNightMode == Configuration.UI_MODE_NIGHT_YES);
        switchCompat.setChecked(isNightModeEnabled);

        // Set the initial sun/moon icon based on the initial switch state
        if (isNightModeEnabled) {
            sun_moon.setImageResource(R.drawable.lighted_moon_24);
        } else {
            sun_moon.setImageResource(R.drawable.baseline_wb_sunny_24);
        }

        switchCompat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                sun_moon.setImageResource(R.drawable.lighted_moon_24);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night_mode", true); // Remove space
                editor.apply();
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                sun_moon.setImageResource(R.drawable.baseline_wb_sunny_24);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("night_mode", false); // Remove space
                editor.apply();
            }
        });
    }
}
