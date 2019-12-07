package com.senyk.volodymyr.officetimelogger.helpers;

import android.content.Context;
import android.text.format.DateUtils;

import com.senyk.volodymyr.officetimelogger.R;
import com.senyk.volodymyr.officetimelogger.exceptions.LogExistsException;
import com.senyk.volodymyr.officetimelogger.exceptions.NoSuchUserException;
import com.senyk.volodymyr.officetimelogger.exceptions.PasswordsMismatchException;

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
                (int) time * 100);
    }

    public String getSuccessfullyLoggedMessage() {
        return this.context.getString(R.string.time_successfully_logged);
    }

    public String getPasswordSuccessfullyChangedMessage() {
        return this.context.getString(R.string.password_successfully_changed);
    }

    public String getErrorMessage(Throwable e) {
        if (e instanceof NoSuchUserException)
            return this.context.getString(R.string.no_such_user_error);
        if (e instanceof PasswordsMismatchException)
            return this.context.getString(R.string.passwords_mismatch_error);
        if (e instanceof LogExistsException)
            return this.context.getString(R.string.log_exists_error);
        return "";
    }
}
