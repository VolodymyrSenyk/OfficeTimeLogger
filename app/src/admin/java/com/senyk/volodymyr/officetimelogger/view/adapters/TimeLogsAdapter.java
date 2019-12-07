package com.senyk.volodymyr.officetimelogger.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;

import java.util.ArrayList;
import java.util.List;

public class TimeLogsAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private List<TimeLogUi> items = new ArrayList<>();

    public TimeLogsAdapter(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
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
        TimeLogViewHolder viewHolder = (TimeLogViewHolder) holder;
        TimeLogUi item = this.items.get(position);

        Context context = viewHolder.itemView.getContext();

        viewHolder.dayOutput.setText(item.getDay());
        viewHolder.totalTimeOutput.setText(context.getString(R.string.total_time_output, item.getTotalTime()));
        viewHolder.arrivalTimeOutput.setText(context.getString(R.string.arrival_time_output, item.getArrivalTime()));
        viewHolder.leavingTimeOutput.setText(context.getString(R.string.leaving_time_output, item.getLeavingTime()));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    private static class TimeLogViewHolder extends RecyclerView.ViewHolder {
        private TextView dayOutput;
        private TextView totalTimeOutput;
        private TextView arrivalTimeOutput;
        private TextView leavingTimeOutput;

        TimeLogViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dayOutput = itemView.findViewById(R.id.day_output);
            this.totalTimeOutput = itemView.findViewById(R.id.total_time_output);
            this.arrivalTimeOutput = itemView.findViewById(R.id.arrival_time_output);
            this.leavingTimeOutput = itemView.findViewById(R.id.leaving_time_output);
        }
    }
}
