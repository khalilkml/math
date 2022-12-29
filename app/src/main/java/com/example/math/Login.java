package com.example.math;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView text_login;
    TextView btn_register;
    Button login;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        text_login = findViewById(R.id.text_login);
        btn_register = findViewById(R.id.btn_register);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> startActivity(new Intent(Login.this, MainActivity.class)));
        btn_register.setOnClickListener(v -> startActivity(new Intent(Login.this, Register.class)));
    }
}