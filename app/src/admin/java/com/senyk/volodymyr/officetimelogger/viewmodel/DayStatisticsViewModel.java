package com.senyk.volodymyr.officetimelogger.viewmodel;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.mappers.dtoui.PairsMapper;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import java.util.List;

public class DayStatisticsViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final PairsMapper pairsMapper;

    private MutableLiveData<List<Pair<UserUi, TimeLogUi>>> logs = new MutableLiveData<>();

    public DayStatisticsViewModel(TimeLoggerRepository repository, PairsMapper pairsMapper) {
        super("DayStatisticsVM");
        this.repository = repository;
        this.pairsMapper = pairsMapper;
    }

    public LiveData<List<Pair<UserUi, TimeLogUi>>> getStatistics() {
        return this.logs;
    }

    public void loadUsersAndLogs(long date) {
    //    this.logs.setValue(pairsMapper.convertToUi(repository.getLogByDay(date)));
    }
}
