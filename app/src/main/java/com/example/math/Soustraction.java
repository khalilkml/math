package com.example.math;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Soustraction extends Fragment {
    TextView full_name ;
    TextView emaile ;
    TextView password ;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Soustraction() {
        // Required empty public constructor
    }


    public static Soustraction newInstance(String param1, String param2) {
        Soustraction fragment = new Soustraction();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soustraction, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        full_name = view.findViewById(R.id.Showfull_name);
        emaile = view.findViewById(R.id.ShowEmail);
        password = view.findViewById(R.id.ShowPassword);
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Toast.makeText(getActivity(), document.getId() + " => " + document.getData(), Toast.LENGTH_LONG).show();
                                full_name.setText(document.getString("Full name"));
                                emaile.setText(document.getString("Email"));
                                password.setText(document.getString("Password"));
                            }
                        } else {
                            Toast.makeText(getActivity(), "Error getting documents.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}