package com.senyk.volodymyr.officetimelogger.view.helpers.datetime;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

public abstract class DateTimeSetter {
    protected final TextInputEditText textView;
    protected final Calendar dateAndTime;

    protected DateTimeSetter(TextInputEditText view) {
        this.textView = view;
        this.dateAndTime = Calendar.getInstance();
    }

    public Calendar getDateAndTime() {
        return this.dateAndTime;
    }

    public void setDateAndTime(long dateAndTime) {
        this.dateAndTime.setTimeInMillis(dateAndTime);
    }

    public abstract void setDialog();
}
