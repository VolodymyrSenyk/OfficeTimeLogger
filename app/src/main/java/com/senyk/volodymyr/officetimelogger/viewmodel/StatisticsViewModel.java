package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.mappers.dtoui.TimeLogsMapper;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import java.util.List;

public class StatisticsViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final TimeLogsMapper mapper;

    private boolean isFiltered;
    private MutableLiveData<List<TimeLogUi>> logs = new MutableLiveData<>();

    public StatisticsViewModel(TimeLoggerRepository repository, TimeLogsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public boolean isFiltered() {
        return this.isFiltered;
    }

    public LiveData<List<TimeLogUi>> getLogs() {
        return this.logs;
    }

    public void loadTimeLogs() {
        this.logs.setValue(mapper.convertToUi(this.repository.getTimeLogs()));
    }

    public void cancelFilters() {
        this.isFiltered = false;
        this.logs.setValue(mapper.convertToUi(this.repository.getTimeLogs()));
    }

    public void loadTimeLogs(long start, long end) {
        this.isFiltered = true;
        this.logs.setValue(mapper.convertToUi(this.repository.getTimeLogs(start, end)));
    }
}
