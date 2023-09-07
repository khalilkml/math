package com.example.math;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.List;

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


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListView taskListView = view.findViewById(R.id.divisionTaskList);

        List<Task> tasks = taskManager.getTasksWithOption("important and urgent");
        boolean hasData = taskManager.hasTasksWithOption("important and urgent");
        if (hasData) {
            Toast.makeText(requireContext(), "Data is available", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(requireContext(), "No data available", Toast.LENGTH_SHORT).show();
        }

        TaskAdapter adapter = new TaskAdapter(requireContext(), tasks);
        taskListView.setAdapter(adapter);
    }


}