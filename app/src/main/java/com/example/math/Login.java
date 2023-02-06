package com.example.math;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    TextView creat_account;
    TextView forget_password;
    TextView check;
    Button login;
    ImageView google;
    ImageView fb;
    FirebaseAuth mAuth;
    FirebaseUser user;
    ProgressBar progressBar;

    public static String PREFS_NAME="MyPrefsFile";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // defining the views(buttons,labels,text views,...)
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        check = findViewById(R.id.check);
        forget_password = findViewById(R.id.btn_forget);
        google = findViewById(R.id.google);
        fb = findViewById(R.id.fb);
        login = findViewById(R.id.login);
        creat_account = findViewById(R.id.btn_register);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.INVISIBLE);
        //firebase variables
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        //handling functions
        forget_password.setOnClickListener(v -> ResetPassword());
        login.setOnClickListener(v -> login());
        creat_account.setOnClickListener(v -> startActivity(new Intent(Login.this, Register.class)));
    }

    //FirebaseAuth.getInstance().signOut();
    //handling reset password function
    private void ResetPassword() {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString().trim())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "The reset Password email is sent to you.", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    // handling login function
    @SuppressLint("SetTextI18n")
    private void login() {
        String Semail = email.getText().toString().trim();
        String Spassword = password.getText().toString().trim();

        if (Semail.isEmpty()) {
            email.setError("email is required");
            email.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(Semail).matches()) {
            email.setError("enter a valid email");
            email.requestFocus();
        } else if (Spassword.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(Semail, Spassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    //*if (user.isEmailVerified()) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Login successfully!", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences(Login.PREFS_NAME,0);
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.putBoolean("hasLoggedIn",true);
                        editor.apply();
                        startActivity(new Intent(Login.this, MainActivity.class));
                        finish();
                   /* } else {
                        user.sendEmailVerification();
                        check.setText("Check your email to verify it.");
                        check.setTextColor(getResources().getColor(R.color.green));
                    }*/
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Login.this, "" + task.getException(), Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}