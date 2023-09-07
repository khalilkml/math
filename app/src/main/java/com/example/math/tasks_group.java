package com.example.math;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class tasks_group extends Fragment {

    public TaskManager taskManager;

    public tasks_group() {
    }

    public static tasks_group newInstance() {
        tasks_group fragment = new tasks_group();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasks_group, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView taskListView = view.findViewById(R.id.divisionTaskList);

        Set<String> tasks = taskManager.getTasksWithOption("important and urgent");
        boolean hasData = taskManager.hasTasksWithOption("important and urgent");
        if (hasData) {
            Toast.makeText(requireContext(), "Data is available", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, new ArrayList<>(tasks));
        taskListView.setAdapter(adapter);
    }

}