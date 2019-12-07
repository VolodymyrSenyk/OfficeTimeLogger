package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;

public class BaseViewModel extends ViewModel {
    protected MutableLiveData<String> message = new SingleEventLiveData<>();

    public LiveData<String> getMessage() {
        return this.message;
    }
}
