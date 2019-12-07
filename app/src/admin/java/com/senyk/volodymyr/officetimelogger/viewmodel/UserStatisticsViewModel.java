package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.mappers.dtoui.TimeLogsMapper;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import java.util.List;

public class UserStatisticsViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final TimeLogsMapper timeLogsMapper;

    private MutableLiveData<List<TimeLogUi>> logs = new MutableLiveData<>();

    public UserStatisticsViewModel(TimeLoggerRepository repository, TimeLogsMapper timeLogsMapper) {
        this.repository = repository;
        this.timeLogsMapper = timeLogsMapper;
    }

    public LiveData<List<TimeLogUi>> getLogs() {
        return this.logs;
    }

    public void loadUserLogs(int monthNumber) {
        this.logs.setValue(timeLogsMapper.convertToUiList(repository.getLogByMonth(monthNumber)));
    }
}
