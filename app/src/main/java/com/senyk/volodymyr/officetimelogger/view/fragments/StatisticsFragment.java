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

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.TimeLogsMapper;
import com.senyk.volodymyr.officetimelogger.repository.FakeRepository;
import com.senyk.volodymyr.officetimelogger.view.adapters.TimeLogsAdapter;
import com.senyk.volodymyr.officetimelogger.view.adapters.TimeLogsEmptyStateAdapter;
import com.senyk.volodymyr.officetimelogger.viewmodel.StatisticsViewModel;

public class StatisticsFragment extends Fragment {
    private StatisticsViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        this.viewModel = new StatisticsViewModel(
                FakeRepository.getFakeRepository(),
                new TimeLogsMapper(new ResourcesProvider(requireContext())));

        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.statistics_screen);
        view.findViewById(R.id.back_button).setOnClickListener(view1 -> {
            if (viewModel.isFiltered()) {
                viewModel.cancelFilters();
            } else {
                NavHostFragment.findNavController(this).popBackStack();
            }
        });
        requireActivity().getOnBackPressedDispatcher()
                .addCallback(this.getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        if (viewModel.isFiltered()) {
                            viewModel.cancelFilters();
                        } else {
                            NavHostFragment.findNavController(StatisticsFragment.this).popBackStack();
                        }
                    }
                });

        Toolbar toolbar = view.findViewById(R.id.statistics_screen_toolbar);
        toolbar.inflateMenu(R.menu.statistics_screen_menu);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);

        RecyclerView dataList = view.findViewById(R.id.statistics_list);
        dataList.setHasFixedSize(true);
        dataList.setNestedScrollingEnabled(false);
        dataList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        TimeLogsAdapter adapter = new TimeLogsAdapter(this.getLayoutInflater());
        TimeLogsEmptyStateAdapter emptyAdapter = new TimeLogsEmptyStateAdapter(this.getLayoutInflater());
        dataList.setAdapter(emptyAdapter);

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

        viewModel.loadTimeLogs();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.statistics_screen_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_set_filter) {
            this.viewModel.loadTimeLogs(0L, 1000L);
        }
        return super.onOptionsItemSelected(item);
    }
}
