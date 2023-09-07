package com.example.math;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.LinkedHashSet;
import java.util.Set;

public class TaskManager {

    private TaskDbHelper dbHelper;

    public TaskManager(Context context) {
        dbHelper = new TaskDbHelper(context);
    }

    public void addTask(String task, String selectedOption) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TASK, task);
        values.put(TaskContract.TaskEntry.COLUMN_SELECTED_OPTION, selectedOption); // Add selected option
        db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        Log.d("TaskManager", "Added task: " + task + ", Selected option: " + selectedOption);
    }

    public void removeTask(String task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = TaskContract.TaskEntry.COLUMN_NAME_TASK + " = ?";
        String[] selectionArgs = {task};
        db.delete(TaskContract.TaskEntry.TABLE_NAME, selection, selectionArgs);
    }

    public Set<String> getTasks() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {TaskContract.TaskEntry.COLUMN_NAME_TASK};
        Cursor cursor = db.query(
                TaskContract.TaskEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                TaskContract.TaskEntry._ID + " DESC"
        );
        Set<String> tasks = new LinkedHashSet<>(); // Use LinkedHashSet to maintain the order of insertion
        while (cursor.moveToNext()) {
            String task = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_TASK));
            tasks.add(task);
        }
        cursor.close();
        return tasks;
    }

    public void changeTask(String oldTask, String newTask, String selectedOption) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TASK, newTask);
        values.put(TaskContract.TaskEntry.COLUMN_SELECTED_OPTION, selectedOption); // Update selected option
        String selection = TaskContract.TaskEntry.COLUMN_NAME_TASK + " = ?";
        String[] selectionArgs = {oldTask};
        db.update(TaskContract.TaskEntry.TABLE_NAME, values, selection, selectionArgs);
    }

    public Set<String> getTasksWithOption(String option) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {TaskContract.TaskEntry.COLUMN_NAME_TASK};
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
        Set<String> tasks = new LinkedHashSet<>(); // Use LinkedHashSet to maintain the order of insertion
        while (cursor.moveToNext()) {
            String task = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_NAME_TASK));
            tasks.add(task);
        }
        cursor.close();

        return tasks;
    }

    public boolean hasTasksWithOption(String option) {
        Set<String> tasks = getTasksWithOption(option);
        boolean hasData = !tasks.isEmpty();
        return hasData;
    }
}

