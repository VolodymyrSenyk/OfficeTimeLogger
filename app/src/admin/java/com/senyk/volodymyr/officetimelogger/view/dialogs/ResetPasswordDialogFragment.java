package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.officetimelogger.R;

public class ResetPasswordDialogFragment extends DialogFragment {
    private static final String USER_ID_BUNDLE_KEY = "user id key";
    private ResetPasswordPositiveButtonClickListener clickListener;

    public static ResetPasswordDialogFragment newInstance(int id) {
        ResetPasswordDialogFragment newFragment = new ResetPasswordDialogFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID_BUNDLE_KEY, id);
        newFragment.setArguments(args);
        return newFragment;
    }

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.reset_password_dialog_fragment_title))
                .setMessage(requireContext().getString(R.string.reset_password_dialog_fragment_message))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) ->
                        clickListener.onPositiveButtonClick(getArguments().getInt(USER_ID_BUNDLE_KEY))
                )
                .setNegativeButton(R.string.dialog_answer_cancel, (dialog, id) -> {
                });
        return dialogBuilder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (ResetPasswordPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
