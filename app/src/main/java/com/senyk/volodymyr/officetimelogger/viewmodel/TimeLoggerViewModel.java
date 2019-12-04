package com.senyk.volodymyr.officetimelogger.viewmodel;

import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

public class TimeLoggerViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final ResourcesProvider resourcesProvider;

    public TimeLoggerViewModel(TimeLoggerRepository repository, ResourcesProvider resourcesProvider) {
        this.repository = repository;
        this.resourcesProvider = resourcesProvider;
    }

    public void addTimeLog(TimeLogDto log) {
        if (this.repository.logTime(log)) {
            this.message.setValue(resourcesProvider.getSuccessfullyLoggedMessage());
        } else {
            this.message.setValue("An error occurred. You made mistake!");
        }
    }
}
