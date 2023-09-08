package com.example.math;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    private List<Task> tasks;

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_card_item, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.titleTextView.setText(task.getTitle());
        holder.notesTextView.setText(task.getTaskNote());
        holder.optionsTextView.setText(task.getSelectedOption());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView notesTextView;
        TextView optionsTextView;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.taskTitle);
            notesTextView = itemView.findViewById(R.id.Task_notes);
            optionsTextView = itemView.findViewById(R.id.selectedOption);
        }
    }
}

