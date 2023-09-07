package com.example.math;

public class Task {
    private String title;
    private String selectedOption;
    private String taskNote;

    public Task(String title, String selectedOption, String taskNote) {
        this.title = title;
        this.selectedOption = selectedOption;
        this.taskNote = taskNote;
    }

    public String getTitle() {
        return title;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public String getTaskNote() {
        return taskNote;
    }
}


