package com.senyk.volodymyr.officetimelogger.view.dialogs;

import android.util.Pair;

public interface ChangePasswordDialogPositiveButtonClickListener {
    void onPositiveButtonClick(Pair<String, String> passwords);
}
