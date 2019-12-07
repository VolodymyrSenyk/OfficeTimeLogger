package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.DateSetter;
import com.senyk.volodymyr.officetimelogger.view.helpers.datetime.DateTimeSetter;

import java.util.Calendar;

public class SetDateFilterDialogFragment extends DialogFragment {
    private DateFilterDialogPositiveButtonClickListener clickListener;

    private DateTimeSetter daySetter;

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.day_filter_dialog_fragment_title))
                .setView(inflater.inflate(R.layout.dialog_fragment_content_day_filter, null))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) -> {
                    Calendar day = daySetter.getDateAndTime();
                    Calendar start = Calendar.getInstance();
                    start.set(Calendar.YEAR, day.get(Calendar.YEAR));
                    start.set(Calendar.MONTH, day.get(Calendar.MONTH));
                    start.set(Calendar.DAY_OF_MONTH, day.get(Calendar.DAY_OF_MONTH));
                    start.set(Calendar.HOUR_OF_DAY, 0);
                    start.set(Calendar.MINUTE, 0);
                    Calendar end = Calendar.getInstance();
                    end.set(Calendar.YEAR, day.get(Calendar.YEAR));
                    end.set(Calendar.MONTH, day.get(Calendar.MONTH));
                    end.set(Calendar.DAY_OF_MONTH, day.get(Calendar.DAY_OF_MONTH));
                    end.set(Calendar.HOUR_OF_DAY, 23);
                    end.set(Calendar.MINUTE, 59);
                    clickListener.onPositiveButtonClick(start.getTimeInMillis(), end.getTimeInMillis());
                })
                .setNegativeButton(R.string.dialog_answer_cancel, (dialog, id) -> {
                });
        return dialogBuilder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            daySetter = new DateSetter(getDialog().findViewById(R.id.day_input_view));
            getDialog().findViewById(R.id.day_input_view).setOnClickListener(view12 -> daySetter.setDialog());
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (DateFilterDialogPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
