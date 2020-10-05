package com.senyk.volodymyr.officetimelogger.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;

public class EmptyStateAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;

    public EmptyStateAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EmptyStateAdapter.TimeLogViewHolder(this.inflater.inflate(
                R.layout.empty_state_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    private static class TimeLogViewHolder extends RecyclerView.ViewHolder {
        TimeLogViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
