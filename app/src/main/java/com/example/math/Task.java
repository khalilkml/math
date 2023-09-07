package com.example.math;

public class Task {
    private String title;
    private String selectedOption;

    public Task(String title, String selectedOption) {
        this.title = title;
        this.selectedOption = selectedOption;
    }

    public String getTitle() {
        return title;
    }

    public String getSelectedOption() {
        return selectedOption;
    }
}

