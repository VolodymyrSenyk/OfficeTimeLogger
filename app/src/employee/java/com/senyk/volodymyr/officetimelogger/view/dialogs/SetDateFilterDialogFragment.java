package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.DateSetter;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.DateTimeSetter;

import java.util.Calendar;

public class SetDateFilterDialogFragment extends DialogFragment {
    private FilterDialogPositiveButtonClickListener clickListener;

    private DateTimeSetter startDateSetter;
    private DateTimeSetter endDateSetter;

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.time_filters_dialog_fragment_title))
                .setView(inflater.inflate(R.layout.dialog_fragment_content_date_filter, null))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) -> {
                    Calendar startTime = startDateSetter.getDateAndTime();
                    startTime.set(Calendar.HOUR_OF_DAY, 0);
                    startTime.set(Calendar.MINUTE, 1);
                    Calendar endTime = endDateSetter.getDateAndTime();
                    endTime.set(Calendar.HOUR_OF_DAY, 23);
                    endTime.set(Calendar.MINUTE, 59);
                    if (startTime.getTimeInMillis() > endTime.getTimeInMillis()) {
                        Toast.makeText(requireContext(), getString(R.string.filters_time_mismatch_error), Toast.LENGTH_LONG).show();
                    } else {
                        clickListener.onPositiveButtonClick(new Pair<>(startTime.getTimeInMillis(), endTime.getTimeInMillis()));
                    }
                })
                .setNegativeButton(R.string.dialog_answer_cancel, (dialog, id) -> {
                });
        return dialogBuilder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            startDateSetter = new DateSetter(getDialog().findViewById(R.id.start_time_input_view));
            endDateSetter = new DateSetter(getDialog().findViewById(R.id.end_time_input_view));
            getDialog().findViewById(R.id.start_time_input_view).setOnClickListener(view12 -> startDateSetter.setDialog());
            getDialog().findViewById(R.id.end_time_input_view).setOnClickListener(view12 -> endDateSetter.setDialog());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (FilterDialogPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
