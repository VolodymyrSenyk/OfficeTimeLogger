package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;
import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

public class AuthorizationViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;

    private MutableLiveData<Integer> userId = new SingleEventLiveData<>();

    public AuthorizationViewModel(TimeLoggerRepository repository) {
        this.repository = repository;
    }

    public LiveData<Integer> getUserId() {
        return this.userId;
    }

    public void logIn(CredentialsDto creds) {
        int userId = this.repository.logIn(creds);
        if (userId != -1) {
            this.userId.setValue(userId);
        } else {
            this.message.setValue("Incorrect credentials!");
        }
    }
}
