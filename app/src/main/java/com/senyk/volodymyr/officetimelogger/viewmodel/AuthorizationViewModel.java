package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;
import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;

public class AuthorizationViewModel extends ViewModel {
    private final TimeLoggerRepository repository;

    private MutableLiveData<String> message = new SingleEventLiveData<>();
    private MutableLiveData<Integer> userId = new SingleEventLiveData<>();

    public AuthorizationViewModel(TimeLoggerRepository repository) {
        this.repository = repository;
    }

    public void logIn(CredentialsDto creds) {
        int userId = this.repository.logIn(creds);
        if (userId != -1) {
            this.userId.setValue(userId);
        } else {
            this.message.setValue("Incorrect credentials");
        }
    }
}
