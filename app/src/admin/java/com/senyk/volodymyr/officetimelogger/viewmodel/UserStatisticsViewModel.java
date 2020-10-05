package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.mappers.dtoui.TimeLogsMapper;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserStatisticsViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final TimeLogsMapper mapper;

    private MutableLiveData<List<TimeLogUi>> logs = new MutableLiveData<>();

    public UserStatisticsViewModel(TimeLoggerRepository repository, TimeLogsMapper timeLogsMapper) {
        super("UserStatisticsVM");
        this.repository = repository;
        this.mapper = timeLogsMapper;
    }

    public LiveData<List<TimeLogUi>> getLogs() {
        return this.logs;
    }

    public void loadUserLogs(int userId, int monthIndex) {
        this.repository.getLogByMonth(userId, monthIndex + 1)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<TimeLogDto>>() {
                    @Override
                    public void onSuccess(List<TimeLogDto> logsDtos) {
                        logs.setValue(mapper.convertToUiList(logsDtos));
                    }
                });
    }
}
