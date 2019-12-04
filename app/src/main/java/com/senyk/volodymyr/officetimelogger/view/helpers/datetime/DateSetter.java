package com.senyk.volodymyr.officetimelogger.view.helpers.datetime;

import android.app.DatePickerDialog;
import android.text.format.DateUtils;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public class DateSetter extends DateTimeSetter {
    public DateSetter(TextInputEditText view) {
        super(view);
        textView.setText(
                DateUtils.formatDateTime(
                        textView.getContext(),
                        dateAndTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                )
        );
    }

    public void setDialog() {
        DatePickerDialog dialog = new DatePickerDialog(
                textView.getContext(),
                (datePicker, year, monthOfYear, dayOfMonth) -> {
                    dateAndTime.set(Calendar.YEAR, year);
                    dateAndTime.set(Calendar.MONTH, monthOfYear);
                    dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    textView.setText(
                            DateUtils.formatDateTime(
                                    textView.getContext(),
                                    dateAndTime.getTimeInMillis(),
                                    DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                            )
                    );
                },
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)
        );
        dialog.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());
        dialog.show();
    }
}
