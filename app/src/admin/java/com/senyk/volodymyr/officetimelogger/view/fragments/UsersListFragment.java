package com.senyk.volodymyr.officetimelogger.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.UsersMapper;
import com.senyk.volodymyr.officetimelogger.repository.NetworkRepository;
import com.senyk.volodymyr.officetimelogger.view.adapters.EmptyStateAdapter;
import com.senyk.volodymyr.officetimelogger.view.adapters.UserClickListener;
import com.senyk.volodymyr.officetimelogger.view.adapters.UsersAdapter;
import com.senyk.volodymyr.officetimelogger.view.dialogs.NewUserCreatorDialogFragment;
import com.senyk.volodymyr.officetimelogger.view.dialogs.NewUserCreatorPositiveButtonClickListener;
import com.senyk.volodymyr.officetimelogger.view.dialogs.ResetPasswordDialogFragment;
import com.senyk.volodymyr.officetimelogger.view.dialogs.ResetPasswordPositiveButtonClickListener;
import com.senyk.volodymyr.officetimelogger.viewmodel.UsersListViewModel;

public class UsersListFragment extends Fragment
        implements UserClickListener, NewUserCreatorPositiveButtonClickListener, ResetPasswordPositiveButtonClickListener {
    private UsersListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        this.viewModel = new UsersListViewModel(
                NetworkRepository.getFakeRepository(),
                new UsersMapper(),
                new ResourcesProvider(requireContext()));

        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.users_list_screen);
        view.findViewById(R.id.back_button).setVisibility(View.GONE);

        Toolbar toolbar = view.findViewById(R.id.users_list_screen_toolbar);
        toolbar.inflateMenu(R.menu.statistics_menu);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);

        RecyclerView dataList = view.findViewById(R.id.users_list);
        dataList.setHasFixedSize(true);
        dataList.setNestedScrollingEnabled(false);
        dataList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        UsersAdapter adapter = new UsersAdapter(this);
        EmptyStateAdapter emptyAdapter = new EmptyStateAdapter(this.getLayoutInflater());
        dataList.setAdapter(emptyAdapter);

        view.findViewById(R.id.add_user).setOnClickListener(view1 -> showNewUserCreatorDialog());

        viewModel.loadUsers();

        this.viewModel.getUsers().observe(this.getViewLifecycleOwner(), users -> {
            if (users.isEmpty()) {
                dataList.setAdapter(emptyAdapter);
            } else {
                adapter.setItems(users);
                dataList.setAdapter(adapter);
            }
        });

        this.viewModel.getMessage().observe(this.getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.statistics_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_show_statistics) {
            NavHostFragment.findNavController(this)
                    .navigate(UsersListFragmentDirections.actionUsersListFragmentToDayStatisticsFragment());
        }
        return super.onOptionsItemSelected(item);
    }

    private void showNewUserCreatorDialog() {
        NewUserCreatorDialogFragment dialog = new NewUserCreatorDialogFragment();
        dialog.setTargetFragment(this, 1);
        dialog.show(requireFragmentManager(), "TAG");
    }

    private void showResetPasswordDialog(int id) {
        ResetPasswordDialogFragment dialog = ResetPasswordDialogFragment.newInstance(id);
        dialog.setTargetFragment(this, 1);
        dialog.show(requireFragmentManager(), "TAG");
    }

    @Override
    public void onResetPasswordButtonClickListener(int id) {
        showResetPasswordDialog(id);
    }

    @Override
    public void onUserClickListener(int id) {
        NavHostFragment.findNavController(this)
                .navigate(UsersListFragmentDirections.actionUsersListFragmentToUserStatisticsFragment(id));
    }

    @Override
    public void onPositiveButtonClick(String firstName, String lastName, String middleName) {
        viewModel.addNewUser(firstName, lastName, middleName);
    }

    @Override
    public void onPositiveButtonClick(int id) {
        viewModel.resetPassword(id);
    }
}
