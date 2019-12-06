package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.officetimelogger.R;

public class ChangePasswordDialogFragment extends DialogFragment {
    private ChangePasswordDialogPositiveButtonClickListener clickListener;

    private TextView oldPasswordField;
    private TextView newPasswordField;

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.change_password_dialog_fragment_title))
                .setView(inflater.inflate(R.layout.dialog_fragment_change_password, null))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) -> {
                    String oldPassword = oldPasswordField.getText().toString();
                    String newPassword = newPasswordField.getText().toString();
                    if (oldPassword.equals("") || newPassword.equals("")) {
                        Toast.makeText(requireContext(), getString(R.string.empty_fields_error), Toast.LENGTH_LONG).show();
                    } else {
                        clickListener.onPositiveButtonClick(new Pair<String, String>(oldPassword, newPassword));
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
            oldPasswordField = getDialog().findViewById(R.id.old_password_input_view);
            newPasswordField = getDialog().findViewById(R.id.new_password_input_view);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (ChangePasswordDialogPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
