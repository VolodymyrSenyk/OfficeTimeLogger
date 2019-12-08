package com.senyk.volodymyr.officetimelogger.view.adapters;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;

import java.util.ArrayList;
import java.util.List;

public class UsersLogsAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private List<Pair<UserUi, TimeLogUi>> items = new ArrayList<>();

    public UsersLogsAdapter(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
    }

    public void setItems(List<Pair<UserUi, TimeLogUi>> newList) {
        this.items = newList;
        notifyDataSetChanged();
    }

    public List<Pair<UserUi, TimeLogUi>> getItems() {
        return this.items;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersLogsAdapter.ViewHolder(this.inflater.inflate(
                R.layout.list_item_user_log_lists, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Pair<UserUi, TimeLogUi> item = this.items.get(position);

        Context context = viewHolder.itemView.getContext();

        String names = item.first.getLastName() + " " +
                item.first.getFirstName().toCharArray()[0] + ". " +
                item.first.getMiddleName().toCharArray()[0] + ".";
        viewHolder.namesOutput.setText(names);
        viewHolder.numberOutput.setText(item.first.getId() + "");
        viewHolder.totalTimeOutput.setText(context.getString(R.string.total_time_output, item.second.getTotalTime()));
        viewHolder.arrivalTimeOutput.setText(context.getString(R.string.arrival_time_output, item.second.getArrivalTime()));
        viewHolder.leavingTimeOutput.setText(context.getString(R.string.leaving_time_output, item.second.getLeavingTime()));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namesOutput;
        private TextView numberOutput;
        private TextView totalTimeOutput;
        private TextView arrivalTimeOutput;
        private TextView leavingTimeOutput;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.namesOutput = itemView.findViewById(R.id.user_names_output);
            this.numberOutput = itemView.findViewById(R.id.user_number_output);
            this.totalTimeOutput = itemView.findViewById(R.id.total_time_output);
            this.arrivalTimeOutput = itemView.findViewById(R.id.arrival_time_output);
            this.leavingTimeOutput = itemView.findViewById(R.id.leaving_time_output);
        }
    }
}
