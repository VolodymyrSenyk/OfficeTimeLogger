package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.senyk.volodymyr.officetimelogger.R;

public class NewUserCreatorDialogFragment extends DialogFragment {
    private NewUserCreatorPositiveButtonClickListener clickListener;

    private TextView firstNameView;
    private TextView lastNameView;
    private TextView middleNameView;

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.user_adding_dialog_fragment_title))
                .setView(inflater.inflate(R.layout.dialog_fragment_content_new_user_creator, null))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) -> {
                    String firstName = firstNameView.getText().toString();
                    String lastName = lastNameView.getText().toString();
                    String middleName = middleNameView.getText().toString();
                    if (firstName.equals("") || lastName.equals("") || middleName.equals("")) {
                        Toast.makeText(requireContext(), getString(R.string.empty_fields_error), Toast.LENGTH_LONG).show();
                    } else {
                        clickListener.onPositiveButtonClick(firstName, lastName, middleName);
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
            firstNameView = getDialog().findViewById(R.id.user_first_name_input_field);
            lastNameView = getDialog().findViewById(R.id.user_last_name_input_field);
            middleNameView = getDialog().findViewById(R.id.user_middle_name_input_field);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (NewUserCreatorPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
