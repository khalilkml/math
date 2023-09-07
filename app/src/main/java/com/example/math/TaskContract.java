package com.example.math;

import android.provider.BaseColumns;

public class TaskContract {

    private TaskContract() {}

    public static class TaskEntry implements BaseColumns {
        public static final String TABLE_NAME = "tasks";
        public static final String COLUMN_NAME_TASK = "task";
        public static final String COLUMN_SELECTED_OPTION = "SELECTED_OPTION";
    }
}

