package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.util.Pair;

public interface DialogPositiveButtonClickListener {
    void onPositiveButtonClick(Pair<Long, Long> dialogData);
}
