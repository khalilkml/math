package com.example.math;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Home extends Fragment {

    ImageView my_organizer;

    ImageView profile;


    Button but2;

    public Home() {
    }

    public static Home newInstance() {
        Home fragment = new Home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        my_organizer = view.findViewById(R.id.my_organizer);
        profile = view.findViewById(R.id.thinking2);

        MainActivity mainActivity = (MainActivity) getActivity();


        my_organizer.setOnClickListener(view1 -> {
            // Call the switchFragment function
            mainActivity.changeToFragment(R.id.somme);
        });

        profile.setOnClickListener(view1 -> {
            mainActivity.changeToFragment(R.id.soustraction);
        });

    }
}