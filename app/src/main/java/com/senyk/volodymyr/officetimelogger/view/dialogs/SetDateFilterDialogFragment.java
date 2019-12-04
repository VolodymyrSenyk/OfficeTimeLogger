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
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.DateTimeSetter;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.TimeSetter;

import java.util.Calendar;

public class SetDateFilterDialogFragment extends DialogFragment {
    private DialogPositiveButtonClickListener clickListener;

    private DateTimeSetter startTimeSetter;
    private DateTimeSetter endTimeSetter;

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.time_filters_dialog_fragment_title))
                .setView(inflater.inflate(R.layout.dialog_fragment_content_date_filter, null))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) -> {
                    Calendar startTime = startTimeSetter.getDateAndTime();
                    Calendar endTime = endTimeSetter.getDateAndTime();
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
            startTimeSetter = new TimeSetter(getDialog().findViewById(R.id.start_time_input_view), TimeSetter.Defaults.ARRIVAL_TIME);
            endTimeSetter = new TimeSetter(getDialog().findViewById(R.id.end_time_input_view), TimeSetter.Defaults.LEAVING_TIME);
            getDialog().findViewById(R.id.start_time_input_view).setOnClickListener(view12 -> startTimeSetter.setDialog());
            getDialog().findViewById(R.id.end_time_input_view).setOnClickListener(view12 -> endTimeSetter.setDialog());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (DialogPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
