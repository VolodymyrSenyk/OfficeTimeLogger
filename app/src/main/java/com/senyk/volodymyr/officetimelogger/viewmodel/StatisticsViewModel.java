package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.officetimelogger.mappers.dtoui.TimeLogsMapper;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;
import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;

import java.util.List;

public class StatisticsViewModel extends ViewModel {
    private final TimeLoggerRepository repository;
    private final TimeLogsMapper mapper;

    private MutableLiveData<List<TimeLogUi>> logs = new MutableLiveData<>();
    private MutableLiveData<String> message = new SingleEventLiveData<>();

    public StatisticsViewModel(TimeLoggerRepository repository, TimeLogsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public void loadTimeLogs() {
        this.logs.setValue(mapper.convertToUi(this.repository.getTimeLogs()));
    }

    public void cancelFilters() {
        this.logs.setValue(mapper.convertToUi(this.repository.getTimeLogs()));
    }

    public void loadTimeLogs(long start, long end) {
        this.logs.setValue(mapper.convertToUi(this.repository.getTimeLogs(start, end)));
    }
}
