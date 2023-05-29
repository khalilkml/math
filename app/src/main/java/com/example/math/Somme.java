package com.example.math;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Somme extends Fragment {


    public TaskManager taskManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskManager = new TaskManager(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_somme, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views and data structures
        EditText taskInput = view.findViewById(R.id.add_task);
        Button addButton = view.findViewById(R.id.addButton);
        Button remove = view.findViewById(R.id.remove);
        Button change = view.findViewById(R.id.change);
        ListView taskList = view.findViewById(R.id.todoList);
        ArrayAdapter adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, new ArrayList<>(taskManager.getTasks()));
        taskList.setAdapter(adapter);

        // Set up click listener for "Add" button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = taskInput.getText().toString().trim();

                if (!task.isEmpty()) {
                    //Returning the task from the editetext
                    taskManager.addTask(task);
                    //Prepare to update the list of tasks
                    adapter.clear();
                    //Update task list
                    adapter.addAll(taskManager.getTasks());
                    //Clear the taskinput
                    taskInput.setText("");
                    Log.d("Somme Fragment", "Added task: " + task);
                }
            }
        });
        taskList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        // Add a listener to the Remove button
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = taskList.getCheckedItemPosition();
                if (position != ListView.INVALID_POSITION) {
                    String task = (String) taskList.getItemAtPosition(position);
                    taskManager.removeTask(task);
                    Log.d("TaskManager", "Selected task: " + task);
                    adapter.remove(task);
                    taskList.clearChoices();
                } else {
                    System.out.println("not");
                }
            }
        });

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = taskList.getCheckedItemPosition();
                if (position != ListView.INVALID_POSITION) {
                    String task = (String) taskList.getItemAtPosition(position);

                    // Create a dialog to get the new task name
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Change Task");

                    // Add a text input to the dialog
                    final EditText input = new EditText(getActivity());
                    input.setText(task);
                    builder.setView(input);

                    // Set the positive button action to change the task
                    builder.setPositiveButton("Change", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String newTask = input.getText().toString();
                            taskManager.changeTask(task, newTask);
                            adapter.remove(task);
                            adapter.insert(newTask, position);
                            taskList.setItemChecked(position, false);
                            taskList.setItemChecked(adapter.getPosition(newTask), true);
                        }
                    });

                    // Set the negative button action to cancel the dialog
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    // Show the dialog
                    builder.show();
                } else {
                    // No item is selected in the list
                    Toast.makeText(getActivity(), "Please select a task to change", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add a listener to the ListView
        taskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                taskList.setItemChecked(position, true);
            }
        });


        // Set up long click listener for list items to delete tasks
        /*taskList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String task = (String) parent.getItemAtPosition(position);
                taskManager.removeTask(task);
                adapter.clear();
                adapter.addAll(taskManager.getTasks());
                return true;
            }
        });*/
    }
}
