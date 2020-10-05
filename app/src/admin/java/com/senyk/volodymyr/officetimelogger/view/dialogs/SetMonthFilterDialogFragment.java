package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.officetimelogger.R;

public class SetMonthFilterDialogFragment extends DialogFragment {
    private MonthFilterPositiveButtonClickListener clickListener;

    private Spinner monthSpinner;

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.month_filter_dialog_fragment_title))
                .setView(inflater.inflate(R.layout.dialog_fragment_content_month_filter, null))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) -> {
                    clickListener.onPositiveButtonClick(monthSpinner.getSelectedItemPosition());
                })
                .setNegativeButton(R.string.dialog_answer_cancel, (dialog, id) -> {
                });
        return dialogBuilder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            monthSpinner = getDialog().findViewById(R.id.month_input_spinner);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (MonthFilterPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
