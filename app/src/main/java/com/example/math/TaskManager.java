package com.example.math;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class TaskManager {

    private TaskDbHelper dbHelper;

    public TaskManager(Context context) {
        dbHelper = new TaskDbHelper(context);
    }

    public void addTask(String task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskEntry.COLUMN_NAME_TASK, task);
        db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values);
        Log.d("TaskManager", "Added task: " + task);
    }

    public void removeTask(String task) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(TaskContract.TaskEntry.TABLE_NAME, TaskContract.TaskEntry.COLUMN_NAME_TASK + " = ?", new String[]{task});
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


    public void changeTask(String oldTask, String newTask) {
        removeTask(oldTask);
        addTask(newTask);
    }
}
