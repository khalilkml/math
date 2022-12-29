package com.example.math;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity {

    private Toolbar topAppBar;

    public MainActivity() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topAppBar = findViewById(R.id.topAppBar);
        setSupportActionBar(topAppBar);

        final DrawerLayout navigation_icon =findViewById(R.id.drawerLayout);
        findViewById(R.id.navigation_up).setOnClickListener(view -> navigation_icon.openDrawer(GravityCompat.START));

        NavigationView navigation_iconst =findViewById(R.id.navigation_icons);
        navigation_iconst.setItemIconTintList(null);

        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigation_iconst ,navController);

        final TextView textTitle = findViewById(R.id.layout_title);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController Controller, @NonNull NavDestination destination, @Nullable Bundle bundle) {
                textTitle.setText(destination.getLabel());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.more) {
            System.out.println("setting");
        } else if(item.getItemId() == R.id.About_us) {
            System.out.println("about us");
        }else{
            System.out.println("error");
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}