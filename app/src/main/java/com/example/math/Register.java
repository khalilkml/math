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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    EditText full_name;
    EditText classe;
    EditText email;

    ImageView fb;

    ImageView google;
    EditText password;
    EditText confirm_password;
    TextView already_have_account;
    Button save;

    GoogleSignInOptions gso;

    GoogleSignInClient gsc;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth;
    ProgressBar progressBar;

    CallbackManager callbackManager;

    int score=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        full_name = findViewById(R.id.full_name);
        classe = findViewById(R.id.classe);
        email = findViewById(R.id.email);
        fb = findViewById(R.id.fb);
        google = findViewById(R.id.google);
        password = findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirm_password);
        already_have_account = findViewById(R.id.already_have_account);
        save = findViewById(R.id.save);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        mAuth = FirebaseAuth.getInstance();
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        startActivity(new Intent(Register.this,MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(@NonNull FacebookException exception) {
                        Toast.makeText(Register.this, "Something went wrong! Try again.", Toast.LENGTH_SHORT).show();
                    }
                });
        already_have_account.setOnClickListener(v -> startActivity(new Intent(Register.this, Login.class)));
        google.setOnClickListener(v -> SingWithGoogle());
        save.setOnClickListener(v -> Registration());
        fb.setOnClickListener(v -> LoginManager.getInstance().logInWithReadPermissions(Register.this, Collections.singletonList("public_profile")));
    }

    private void SingWithGoogle() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                navigateToSecond();
            } catch (ApiException e) {
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void navigateToSecond() {
        finish();
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
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
        } else if (SSconfirm_password.isEmpty() || !(SSconfirm_password.matches(SSpassword))) {
            confirm_password.setError("email is required and it has to be the same as the password");
            confirm_password.requestFocus();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            //FIRESTORE Create a new user
            Map<String, Object> client = new HashMap<>();
            client.put("Full name", SSfull_name);
            client.put("Classe", SSclasse);
            client.put("Email", SSemail);
            client.put("Password", SSpassword);
            client.put("Score",score);

            mAuth.createUserWithEmailAndPassword(SSemail, SSpassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    // Add a new client to FIRESTORE with a generated ID
                    db.collection("users")
                            .add(client)
                            .addOnSuccessListener(documentReference -> {
                                //DocumentReference.getId()
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "Registered successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Register.this, MainActivity.class));
                            })
                            .addOnFailureListener(e -> {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();


                            });
                } else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Register.this, "Failed to register! Try again!", Toast.LENGTH_SHORT).show();
                }
            });

            /*mAuth.createUserWithEmailAndPassword(SSemail, SSpassword).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    User user = new User(SSfull_name, SSclasse, SSemail, SSpassword, SSconfirm_password);
                    FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this, "Registered successful", Toast.LENGTH_SHORT).show();
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
            });**/
        }
    }
}