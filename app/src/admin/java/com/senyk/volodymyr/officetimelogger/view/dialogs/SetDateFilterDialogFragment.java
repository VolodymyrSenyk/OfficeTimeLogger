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
                    clickListener.onPositiveButtonClick(day.getTimeInMillis());
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
