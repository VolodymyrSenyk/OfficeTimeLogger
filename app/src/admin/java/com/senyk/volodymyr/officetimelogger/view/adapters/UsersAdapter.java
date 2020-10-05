package com.senyk.volodymyr.officetimelogger.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;

import java.util.ArrayList;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter {
    private final LayoutInflater inflater;
    private final UserClickListener listener;
    private List<UserUi> items = new ArrayList<>();

    public UsersAdapter(Fragment fragment) {
        this.inflater = fragment.getLayoutInflater();
        this.listener = (UserClickListener) fragment;
    }

    public void setItems(List<UserUi> newList) {
        this.items = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UsersAdapter.ViewHolder(this.inflater.inflate(
                R.layout.list_item_users_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        UserUi item = this.items.get(position);

        Context context = viewHolder.itemView.getContext();

        String names = item.getLastName() + " " +
                item.getFirstName() + " " +
                item.getMiddleName();
        viewHolder.namesOutput.setText(names);
        viewHolder.numberOutput.setText(context.getString(R.string.user_number_output, item.getId()));

        viewHolder.itemView.setOnClickListener(view -> listener.onUserClickListener(item.getId()));
        viewHolder.resetPasswordButton.setOnClickListener(view -> listener.onResetPasswordButtonClickListener(item.getId()));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView namesOutput;
        private TextView numberOutput;
        private AppCompatImageButton resetPasswordButton;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.namesOutput = itemView.findViewById(R.id.user_names_output);
            this.numberOutput = itemView.findViewById(R.id.user_number_output);
            this.resetPasswordButton = itemView.findViewById(R.id.reset_password_button);
        }
    }
}
