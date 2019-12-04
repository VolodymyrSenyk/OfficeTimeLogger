package com.senyk.volodymyr.officetimelogger.view.helpers.datetime;

import android.app.TimePickerDialog;
import android.text.format.DateUtils;

import com.google.android.material.textfield.TextInputEditText;
import com.senyk.volodymyr.officetimelogger.R;

import java.util.Calendar;

public class TimeSetter extends DateTimeSetter {
    public TimeSetter(TextInputEditText view) {
        super(view);
        textView.setText(
                DateUtils.formatDateTime(
                        textView.getContext(),
                        dateAndTime.getTimeInMillis(),
                        DateUtils.FORMAT_SHOW_TIME
                )
        );
    }

    public void setDialog() {
        TimePickerDialog dialog = new TimePickerDialog(
                textView.getContext(),
                R.style.TimePickerDialogTheme,
                (timePicker, hourOfDay, minute) -> {
                    dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    dateAndTime.set(Calendar.MINUTE, minute);
                    textView.setText(
                            DateUtils.formatDateTime(
                                    textView.getContext(),
                                    dateAndTime.getTimeInMillis(),
                                    DateUtils.FORMAT_SHOW_TIME
                            )
                    );
                },
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true
        );
        dialog.show();
    }
}
