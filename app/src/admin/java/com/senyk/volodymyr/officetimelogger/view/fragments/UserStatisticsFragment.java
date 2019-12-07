package com.senyk.volodymyr.officetimelogger.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.TimeLogsMapper;
import com.senyk.volodymyr.officetimelogger.repository.NetworkRepository;
import com.senyk.volodymyr.officetimelogger.view.adapters.EmptyStateAdapter;
import com.senyk.volodymyr.officetimelogger.view.adapters.TimeLogsAdapter;
import com.senyk.volodymyr.officetimelogger.view.dialogs.MonthFilterPositiveButtonClickListener;
import com.senyk.volodymyr.officetimelogger.view.dialogs.SetMonthFilterDialogFragment;
import com.senyk.volodymyr.officetimelogger.viewmodel.UserStatisticsViewModel;

public class UserStatisticsFragment extends Fragment implements MonthFilterPositiveButtonClickListener {
    private UserStatisticsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        this.viewModel = new UserStatisticsViewModel(
                NetworkRepository.getFakeRepository(),
                new TimeLogsMapper(new ResourcesProvider(requireContext())));

        RecyclerView dataList = view.findViewById(R.id.statistics_list);
        dataList.setHasFixedSize(true);
        dataList.setNestedScrollingEnabled(false);
        dataList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        TimeLogsAdapter adapter = new TimeLogsAdapter(this);
        EmptyStateAdapter emptyAdapter = new EmptyStateAdapter(this.getLayoutInflater());
        dataList.setAdapter(emptyAdapter);

        view.findViewById(R.id.back_button).setOnClickListener(view1 ->
                NavHostFragment.findNavController(this).popBackStack()
        );

        this.viewModel.getLogs().observe(this.getViewLifecycleOwner(), timeLogs -> {
            if (timeLogs.isEmpty()) {
                dataList.setAdapter(emptyAdapter);
            } else {
                adapter.setItems(timeLogs);
                dataList.setAdapter(adapter);
            }
        });

        this.viewModel.getMessage().observe(this.getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());

        showSetFiltersDialog();
    }

    private void showSetFiltersDialog() {
        SetMonthFilterDialogFragment dialog = new SetMonthFilterDialogFragment();
        dialog.setTargetFragment(this, 1);
        dialog.show(requireFragmentManager(), "TAG");
    }

    @Override
    public void onPositiveButtonClick(int monthIndex) {
        viewModel.loadUserLogs(monthIndex);
    }
}
