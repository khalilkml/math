package com.example.math;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


public class add_task extends Fragment {


    public TaskManager taskManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskManager = new TaskManager(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_task, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views and data structures
        EditText taskInput = requireView().findViewById(R.id.add_task);
        EditText taskNotesInput = view.findViewById(R.id.add_notes);
        Button addButton = view.findViewById(R.id.addButton);
//        Button remove = view.findViewById(R.id.remove);
//        Button change = view.findViewById(R.id.change);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, new ArrayList<>(taskManager.getTasks()));
//        taskList.setAdapter(adapter);

        // Set up click listener for "Add" button
//        addButton.setOnClickListener(v -> {
//            // Create a dialog to choose an option
//            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//            builder.setTitle("Choose an Option");
//
//            // Set the single-choice options
//            String[] singleChoiceOptions = {"important and urgent", "important but not urgent", "not important but urgent", "not important and not urgent"};
//            final int[] checkedItem = {0}; // Default checked item
//
//            builder.setSingleChoiceItems(singleChoiceOptions, checkedItem[0], (dialog, which) -> {
//                // Handle option selection
//                checkedItem[0] = which; // Update the checked item index
//            });
//
//            // Set the positive button action to add the task
//            builder.setPositiveButton("Add", (dialog, which) -> {
//                String task = taskInput.getText().toString().trim();
//
//                if (!task.isEmpty()) {
//                    // Process the selected option
//                    String selectedOption = singleChoiceOptions[checkedItem[0]];
//                    Log.d("Selected Option", selectedOption);
//
//                    // Add the task with the selected option
//                    taskManager.addTask(task, selectedOption);
//                    adapter.clear();
//                    adapter.addAll(taskManager.getTasks());
//                    taskInput.setText("");
//
//                    Log.d("Somme Fragment", "Added task: " + task);
//                }
//            });
//
//            // Set the negative button action to cancel the dialog
//            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
//
//            // Show the dialog
//            builder.show();
//        });
        addButton.setOnClickListener(v -> {
            // Create a dialog to choose an option
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Choose an Option");

            // Set the single-choice options
            String[] singleChoiceOptions = {"important and urgent", "important but not urgent", "not important but urgent", "not important and not urgent"};
            final int[] checkedItem = {0}; // Default checked item

            builder.setSingleChoiceItems(singleChoiceOptions, checkedItem[0], (dialog, which) -> {
                // Handle option selection
                checkedItem[0] = which; // Update the checked item index
            });

            // Set the positive button action to add the task
            builder.setPositiveButton("Add", (dialog, which) -> {
                String task = taskInput.getText().toString().trim();
                String taskNotes = taskNotesInput.getText().toString().trim(); // Get task notes input

                if (!task.isEmpty()) {
                    // Process the selected option
                    String selectedOption = singleChoiceOptions[checkedItem[0]];
                    Log.d("Selected Option", selectedOption);

                    // Add the task with the selected option and notes
                    taskManager.addTask(task, selectedOption, taskNotes);

                    // Clear input fields
                    taskInput.setText("");
                    taskNotesInput.setText("");

                    Log.d("Some Fragment", "Added task: " + task);
                }
            });

            // Set the negative button action to cancel the dialog
            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            // Show the dialog
            builder.show();
        });
    }
}

//        taskList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Add a listener to the Remove button
//        remove.setOnClickListener(v -> {
//            int position = taskList.getCheckedItemPosition();
//            if (position != ListView.INVALID_POSITION) {
//                String task = (String) taskList.getItemAtPosition(position);
//                taskManager.removeTask(task);
//                Log.d("TaskManager", "Selected task: " + task);
//                adapter.remove(task);
//                taskList.clearChoices();
//            } else {
//                System.out.println("not");
//            }
//        });
//
//        change.setOnClickListener(v -> {
//            int position = taskList.getCheckedItemPosition();
//            if (position != ListView.INVALID_POSITION) {
//                String task = (String) taskList.getItemAtPosition(position);
//
//                // Create a dialog to get the new task name and select an option
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                builder.setTitle("Change Task");
//
//                // Add a text input to the dialog
//                final EditText input = new EditText(getActivity());
//                input.setText(task);
//                builder.setView(input);
//
//                // Set the single-choice options
//                String[] singleChoiceOptions = {"important and urgent ", "important but not urgent", "not important but urgent", "not important and not urgent"};
//                final int[] checkedItem = {0}; // Default checked item
//
//                builder.setSingleChoiceItems(singleChoiceOptions, checkedItem[0], (dialog, which) -> {
//                    // Handle option selection
//                    checkedItem[0] = which; // Update the checked item index
//                });
//
//                // Set the positive button action to change the task
//                builder.setPositiveButton("Change", (dialog, which) -> {
//                    String newTask = input.getText().toString();
//                    String selectedOption = singleChoiceOptions[checkedItem[0]]; // Get the selected option
//                    taskManager.changeTask(task, newTask, selectedOption); // Pass all three arguments
//                    adapter.remove(task);
//                    adapter.insert(newTask, position);
//                    taskList.setItemChecked(position, false);
//                    taskList.setItemChecked(adapter.getPosition(newTask), true);
//
//                    Log.d("Selected Option", selectedOption);
//                });
//
//
//                // Set the negative button action to cancel the dialog
//                builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
//
//                // Show the dialog
//                builder.show();
//            } else {
//                // No item is selected in the list
//                Toast.makeText(getActivity(), "Please select a task to change", Toast.LENGTH_SHORT).show();
//            }
//        });



//
//        // Add a listener to the ListView
//        taskList.setOnItemClickListener((parent, view1, position, id) -> {
//            taskList.setItemChecked(position, true);
//            // Show the button
//            remove.setVisibility(View.VISIBLE);
//            change.setVisibility(View.VISIBLE);
//            // Apply animations (e.g., slide up animation)
//            Animation slideUp = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_up);
//            remove.startAnimation(slideUp);
//            change.startAnimation(slideUp);
//        });
//        taskList.setOnTouchListener((v, event) -> {
//            // Clear item selection when touching outside the list
//            taskList.clearChoices();
//            remove.setVisibility(View.GONE);
//            change.setVisibility(View.GONE);
//            return false;
//        });

