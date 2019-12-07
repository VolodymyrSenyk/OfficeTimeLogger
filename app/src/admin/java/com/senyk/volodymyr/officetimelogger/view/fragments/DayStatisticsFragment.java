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
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.PairsMapper;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.TimeLogsMapper;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.UsersMapper;
import com.senyk.volodymyr.officetimelogger.repository.FakeRepository;
import com.senyk.volodymyr.officetimelogger.view.adapters.EmptyStateAdapter;
import com.senyk.volodymyr.officetimelogger.view.adapters.UsersLogsAdapter;
import com.senyk.volodymyr.officetimelogger.view.dialogs.DateFilterDialogPositiveButtonClickListener;
import com.senyk.volodymyr.officetimelogger.view.dialogs.SetDateFilterDialogFragment;
import com.senyk.volodymyr.officetimelogger.viewmodel.DayStatisticsViewModel;

public class DayStatisticsFragment extends Fragment implements DateFilterDialogPositiveButtonClickListener {
    private DayStatisticsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        this.viewModel = new DayStatisticsViewModel(
                FakeRepository.getFakeRepository(),
                new PairsMapper(
                        new TimeLogsMapper(new ResourcesProvider(requireContext())),
                        new UsersMapper()));

        RecyclerView dataList = view.findViewById(R.id.statistics_list);
        dataList.setHasFixedSize(true);
        dataList.setNestedScrollingEnabled(false);
        dataList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        UsersLogsAdapter adapter = new UsersLogsAdapter(this);
        EmptyStateAdapter emptyAdapter = new EmptyStateAdapter(this.getLayoutInflater());
        dataList.setAdapter(emptyAdapter);

        view.findViewById(R.id.back_button).setOnClickListener(view1 ->
                NavHostFragment.findNavController(this).popBackStack()
        );

        this.viewModel.getStatistics().observe(this.getViewLifecycleOwner(), statistics -> {
            if (statistics.isEmpty()) {
                dataList.setAdapter(emptyAdapter);
            } else {
                adapter.setItems(statistics);
                dataList.setAdapter(adapter);
            }
        });

        this.viewModel.getMessage().observe(this.getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());

        showSetFiltersDialog();
    }


    private void showSetFiltersDialog() {
        SetDateFilterDialogFragment dialog = new SetDateFilterDialogFragment();
        dialog.setTargetFragment(this, 1);
        dialog.show(requireFragmentManager(), "TAG");
    }

    @Override
    public void onPositiveButtonClick(long date) {
        viewModel.loadUsersAndLogs(date);
    }
}
