package com.example.math;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class Home extends Fragment {

    private RecyclerView cardRecyclerView;
    private CardAdapter cardAdapter;
    private TaskManager taskManager;

    // ... Other view references and variables

    public Home() {
    }

    public static Home newInstance() {
        Home fragment = new Home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskManager = new TaskManager(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardRecyclerView = view.findViewById(R.id.cardRecyclerView);
        cardAdapter = new CardAdapter();
        cardRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        cardRecyclerView.setAdapter(cardAdapter);

        // Load tasks from the SQLite database
        List<Task> tasks = taskManager.getAllTasks();

        // Add tasks to the card adapter
        cardAdapter.setTasks(tasks);

        Button AddNewTask;

        AddNewTask = view.findViewById(R.id.add_new_task);
//        profile = view.findViewById(R.id.thinking2);

        MainActivity mainActivity = (MainActivity) getActivity();


        AddNewTask.setOnClickListener(view1 -> {
            // Call the switchFragment function
            mainActivity.changeToFragment(R.id.somme);
        });

//        profile.setOnClickListener(view1 -> {
//            mainActivity.changeToFragment(R.id.soustraction);
//        });


    }
}