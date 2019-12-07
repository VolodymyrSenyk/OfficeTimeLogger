package com.senyk.volodymyr.officetimelogger.helpers;

import android.content.Context;
import android.text.format.DateUtils;

import com.senyk.volodymyr.officetimelogger.R;

import java.util.Calendar;

public class ResourcesProvider {
    private final Context context;

    public ResourcesProvider(Context context) {
        this.context = context;
    }

    public String getDay(long date) {
        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(date);
        return DateUtils.formatDateTime(
                this.context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
        );
    }

    public String getTime(long time) {
        Calendar dateAndTime = Calendar.getInstance();
        dateAndTime.setTimeInMillis(time);
        return DateUtils.formatDateTime(
                this.context,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_TIME
        );
    }

    public String getTotalTime(double time) {
        return this.context.getString(
                R.string.total_time_format,
                (int) time,
                (int) (time % 1 * 100));
    }

    public String getPasswordSuccessfullyResetMessage() {
        return this.context.getString(R.string.reset_password_success_message);
    }
}
