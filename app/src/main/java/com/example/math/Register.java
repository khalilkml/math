package com.example.math;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {

    EditText full_name;
    EditText classe;
    EditText email;
    EditText password;
    EditText confirm_password;
    TextView already_have_account;
    Button save;

    FirebaseAuth mAuth;
    ProgressBar progressBar;

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
        save = findViewById(R.id.save);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();

        already_have_account.setOnClickListener(v -> startActivity(new Intent(Register.this, Login.class)));

        save.setOnClickListener(v -> Registration());
    }

    private void Registration() {
        String SSfull_name = full_name.getText().toString();
        String SSclasse = classe.getText().toString();
        String SSemail = email.getText().toString();
        String SSpassword = password.getText().toString();
        String SSconfirm_password = confirm_password.getText().toString();

        if (SSfull_name.isEmpty()) {
            full_name.setError("full name is required");
            full_name.requestFocus();
        } else if (SSclasse.isEmpty()) {
            classe.setError("class is required");
            classe.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(SSemail).matches()) {
            email.setError("write a valid email");
            email.requestFocus();
        } else if (SSpassword.isEmpty() || SSpassword.length() < 6) {
            password.setError("password is required and it has to be more than 6 characters");
            password.requestFocus();
        } else if (SSconfirm_password.isEmpty()) {
            confirm_password.setError("email is required and it has to be the same as the password");
            confirm_password.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(SSemail, SSpassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            User user = new User(SSfull_name, SSclasse, SSemail, SSpassword, SSconfirm_password);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            progressBar.setVisibility(View.GONE);
                                            Log.d(TAG, "Successfully signed in with email link!");
                                            startActivity(new Intent(Register.this, MainActivity.class));
                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(Register.this, "" + task1.getException(), Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
}
