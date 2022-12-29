package com.example.math;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class Register extends AppCompatActivity {

    EditText full_name;
    EditText classe;
    EditText email;
    EditText password;
    EditText confirm_password;
    TextView already_have_account;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        full_name = findViewById(R.id.full_name);
        classe = findViewById(R.id.classe);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        already_have_account = findViewById(R.id.already_have_account);

        mAuth = FirebaseAuth.getInstance();

        already_have_account.setOnClickListener(v -> startActivity(new Intent(Register.this, Login.class)));
    }
}