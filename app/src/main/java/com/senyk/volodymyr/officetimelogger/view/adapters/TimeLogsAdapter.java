package com.senyk.volodymyr.officetimelogger.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;

import java.util.ArrayList;
import java.util.List;

public class TimeLogsAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;

    private List<TimeLogUi> items = new ArrayList<>();

    public TimeLogsAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    public void setItems(List<TimeLogUi> newList) {
        this.items = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TimeLogsAdapter.TimeLogViewHolder(this.inflater.inflate(
                R.layout.list_item_time_log_output, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class TimeLogViewHolder extends RecyclerView.ViewHolder {
        TimeLogViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
