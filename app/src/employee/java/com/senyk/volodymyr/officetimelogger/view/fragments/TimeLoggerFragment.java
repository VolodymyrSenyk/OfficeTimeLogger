package com.senyk.volodymyr.officetimelogger.view.fragments;

import android.os.Bundle;
import android.util.Pair;
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

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.mappers.dtoui.UserMapper;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.repository.NetworkRepository;
import com.senyk.volodymyr.officetimelogger.view.dialogs.ChangePasswordDialogFragment;
import com.senyk.volodymyr.officetimelogger.view.dialogs.ChangePasswordDialogPositiveButtonClickListener;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.DateSetter;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.DateTimeSetter;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.TimeSetter;
import com.senyk.volodymyr.officetimelogger.viewmodel.TimeLoggerViewModel;

import java.util.Calendar;

public class TimeLoggerFragment extends Fragment implements ChangePasswordDialogPositiveButtonClickListener {
    private TimeLoggerViewModel viewModel;

    private DateTimeSetter daySetter;
    private DateTimeSetter arrivalTimeSetter;
    private DateTimeSetter leavingTimeSetter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_time_logger, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        this.viewModel = new TimeLoggerViewModel(
                NetworkRepository.getFakeRepository(),
                new ResourcesProvider(requireContext()), new UserMapper());

        ((TextView) view.findViewById(R.id.screen_title)).setText(R.string.time_logger_screen);
        view.findViewById(R.id.back_button).setVisibility(View.GONE);

        Toolbar toolbar = view.findViewById(R.id.time_logger_screen_toolbar);
        toolbar.inflateMenu(R.menu.time_logger_screen_menu);
        toolbar.setOnMenuItemClickListener(this::onOptionsItemSelected);

        daySetter = new DateSetter(view.findViewById(R.id.day_input_view));
        arrivalTimeSetter = new TimeSetter(view.findViewById(R.id.arrival_time_input_view), TimeSetter.Defaults.ARRIVAL_TIME);
        leavingTimeSetter = new TimeSetter(view.findViewById(R.id.leaving_time_input_view), TimeSetter.Defaults.LEAVING_TIME);

        view.findViewById(R.id.day_input_view).setOnClickListener(view12 -> daySetter.setDialog());
        view.findViewById(R.id.arrival_time_input_view).setOnClickListener(view12 -> arrivalTimeSetter.setDialog());
        view.findViewById(R.id.leaving_time_input_view).setOnClickListener(view12 -> leavingTimeSetter.setDialog());

        view.findViewById(R.id.change_password_button).setOnClickListener(view12 -> showChangePasswordDialog());

        view.findViewById(R.id.add_log_button).setOnClickListener(view1 -> {
            Calendar day = daySetter.getDateAndTime();
            Calendar arrivalTime = arrivalTimeSetter.getDateAndTime();
            Calendar leavingTime = leavingTimeSetter.getDateAndTime();

            if (arrivalTime.getTimeInMillis() > leavingTime.getTimeInMillis()) {
                Toast.makeText(requireContext(), getString(R.string.time_mismatch_error), Toast.LENGTH_LONG).show();
            } else {
                Calendar arrival = Calendar.getInstance();
                arrival.setTimeInMillis(day.getTimeInMillis());
                arrival.set(Calendar.HOUR_OF_DAY, arrivalTime.get(Calendar.HOUR_OF_DAY));
                arrival.set(Calendar.MINUTE, arrivalTime.get(Calendar.MINUTE));
                Calendar leaving = Calendar.getInstance();
                leaving.setTimeInMillis(day.getTimeInMillis());
                leaving.set(Calendar.HOUR_OF_DAY, leavingTime.get(Calendar.HOUR_OF_DAY));
                leaving.set(Calendar.MINUTE, arrivalTime.get(Calendar.MINUTE));
                viewModel.addTimeLog(
                        new TimeLogDto(
                                arrival.getTimeInMillis(),
                                leavingTime.getTimeInMillis()
                        )
                );
            }
        });

        requireActivity().getOnBackPressedDispatcher()
                .addCallback(this.getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });

        this.viewModel.getUserData().observe(this.getViewLifecycleOwner(), userData -> {
            String names = userData.getLastName() + " " + userData.getFirstName().toCharArray()[0] + ". " +
                    userData.getMiddleName().toCharArray()[0] + ".";
            ((TextView) view.findViewById(R.id.first_last_middle_name_output)).setText(names);
        });

        this.viewModel.getMessage().observe(this.getViewLifecycleOwner(), message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show());

        this.viewModel.loadUserData();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.time_logger_screen_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_show_statistics) {
            NavHostFragment.findNavController(this)
                    .navigate(TimeLoggerFragmentDirections.actionTimeLoggerFragmentToStatisticsFragments());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPositiveButtonClick(Pair<String, String> passwords) {
        viewModel.changePassword(passwords);
    }

    private void showChangePasswordDialog() {
        ChangePasswordDialogFragment dialog = new ChangePasswordDialogFragment();
        dialog.setTargetFragment(this, 1);
        dialog.show(requireFragmentManager(), "TAG");
    }
}
