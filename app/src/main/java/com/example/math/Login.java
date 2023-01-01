package com.example.math;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView creat_account;
    TextView forget_password;
    Button login;
    ImageView google;
    ImageView fb;
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forget_password = findViewById(R.id.btn_forget);
        google = findViewById(R.id.google);
        fb = findViewById(R.id.fb);
        login = findViewById(R.id.login);
        creat_account = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> login());
        creat_account.setOnClickListener(v -> startActivity(new Intent(Login.this, Register.class)));
    }

    private void login() {
        String Semail = email.getText().toString().trim();
        String Spassword = password.getText().toString().trim();

        if (Semail.isEmpty()) {
            email.setError("email is required");
            email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Semail).matches()) {
            password.setError("enter a valid email");
            password.requestFocus();
        } else if (Spassword.isEmpty()) {
            password.setError("class is required");
            password.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(Semail, Spassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    System.out.println("first if");
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "login successfully ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                } else {
                    System.out.println("else ");
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}