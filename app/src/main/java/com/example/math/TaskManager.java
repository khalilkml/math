package com.example.math;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private TaskDbHelper dbHelper;

    public TaskManager(Context context) {
        dbHelper = new TaskDbHelper(context);
    }

    public void addTask(String task, String selectedOption, String taskNote) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TASK, task);
        values.put(TaskContract.TaskEntry.COLUMN_SELECTED_OPTION, selectedOption);
        values.put(TaskContract.TaskEntry.COLUMN_TASK_NOTE, taskNote); // Add task note
        db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        Log.d("TaskManager", "Added task: " + task + ", Selected option: " + selectedOption);
    }


    public void removeTask(String task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = TaskContract.TaskEntry.COLUMN_NAME_TASK + " = ?";
        String[] selectionArgs = {task};
        db.delete(TaskContract.TaskEntry.TABLE_NAME, selection, selectionArgs);
    }


    public List<Task> getTasks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                TaskContract.TaskEntry.COLUMN_NAME_TASK,
                TaskContract.TaskEntry.COLUMN_SELECTED_OPTION, // Include selected option if needed
                TaskContract.TaskEntry.COLUMN_TASK_NOTE // Include task notes column
        };

        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                TaskContract.TaskEntry._ID + " DESC"
        );

        List<Task> tasks = new ArrayList<>();
        while (cursor.moveToNext()) {
            String taskName = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_TASK));
            String selectedOption = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_SELECTED_OPTION)); // Include if needed
            String taskNotes = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TASK_NOTE)); // Retrieve task notes

            Task task = new Task(taskName, selectedOption, taskNotes); // Update Task class constructor accordingly
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }


    public void changeTask(String oldTask, String newTask, String selectedOption, String newTaskNotes) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TASK, newTask);
        values.put(TaskContract.TaskEntry.COLUMN_SELECTED_OPTION, selectedOption); // Update selected option
        values.put(TaskContract.TaskEntry.COLUMN_TASK_NOTE, newTaskNotes); // Update task notes
        String selection = TaskContract.TaskEntry.COLUMN_NAME_TASK + " = ?";
        String[] selectionArgs = {oldTask};
        db.update(TaskContract.TaskEntry.TABLE_NAME, values, selection, selectionArgs);
    }


    public List<Task> getTasksWithOption(String option) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                TaskContract.TaskEntry.COLUMN_NAME_TASK,
                TaskContract.TaskEntry.COLUMN_SELECTED_OPTION,
                TaskContract.TaskEntry.COLUMN_TASK_NOTE // Include task notes in projection
        };
        String selection = TaskContract.TaskEntry.COLUMN_SELECTED_OPTION + " = ?";
        String[] selectionArgs = {option};
        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                TaskContract.TaskEntry._ID + " DESC"
        );
        List<Task> tasks = new ArrayList<>(); // Use ArrayList to store Task objects
        while (cursor.moveToNext()) {
            String taskTitle = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_TASK));
            String selectedOption = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_SELECTED_OPTION));
            String taskNotes = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TASK_NOTE));

            Task task = new Task(taskTitle, selectedOption, taskNotes); // Create a Task object with title, option, and notes
            tasks.add(task);
        }
        cursor.close();

        return tasks;
    }


    public boolean hasTasksWithOption(String option) {
        List<Task> tasks = getTasksWithOption(option);
        boolean hasData = !tasks.isEmpty();
        return hasData;
    }

    public List<Task> getAllTasks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                TaskContract.TaskEntry.COLUMN_NAME_TASK,
                TaskContract.TaskEntry.COLUMN_SELECTED_OPTION,
                TaskContract.TaskEntry.COLUMN_TASK_NOTE // Include task notes in projection
        };
        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                TaskContract.TaskEntry._ID + " DESC"
        );

        List<Task> tasks = new ArrayList<>();

        while (cursor.moveToNext()) {
            String task = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_TASK));
            String selectedOption = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_SELECTED_OPTION));
            String taskNotes = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TASK_NOTE));

            Task newTask = new Task(task, selectedOption, taskNotes);
            tasks.add(newTask);
        }
        cursor.close();
        return tasks;
    }
}

