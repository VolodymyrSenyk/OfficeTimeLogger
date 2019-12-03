package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;
import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;

public class TimeLoggerViewModel extends ViewModel {
    private final TimeLoggerRepository repository;

    private MutableLiveData<String> message = new SingleEventLiveData<>();

    public TimeLoggerViewModel(TimeLoggerRepository repository) {
        this.repository = repository;
    }

    public void addTimeLog(TimeLogDto log) {
        if (this.repository.logTime(log)) {
            this.message.setValue("Time successfully logged");
        } else {
            this.message.setValue("An error occurred");
        }
    }
}
