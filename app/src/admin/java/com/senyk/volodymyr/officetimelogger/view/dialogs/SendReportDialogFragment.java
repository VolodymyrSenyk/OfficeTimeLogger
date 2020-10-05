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

public class SendReportDialogFragment extends DialogFragment {
    private SendReportPositiveButtonClickListener clickListener;

    private TextView addressView;
    private TextView subjectView;
    private TextView messageView;

    @SuppressLint("InflateParams")
    @Override
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
        dialogBuilder.setTitle(requireContext().getString(R.string.send_email_dialog_fragment_title))
                .setView(inflater.inflate(R.layout.dialog_fragment_content_send_report, null))
                .setPositiveButton(R.string.dialog_answer_continue, (dialog, id) -> {
                    String address = addressView.getText().toString();
                    String subject = subjectView.getText().toString();
                    String message = messageView.getText().toString();
                    if (address.equals("") || subject.equals("") || message.equals("")) {
                        Toast.makeText(requireContext(), getString(R.string.empty_fields_error), Toast.LENGTH_LONG).show();
                    } else {
                        clickListener.onPositiveButtonClick(address, subject, message);
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
            addressView = getDialog().findViewById(R.id.send_email_address_input_field);
            subjectView = getDialog().findViewById(R.id.send_email_subject_input_field);
            messageView = getDialog().findViewById(R.id.send_email_message_input_field);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.clickListener = (SendReportPositiveButtonClickListener) getTargetFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.clickListener = null;
    }

}
