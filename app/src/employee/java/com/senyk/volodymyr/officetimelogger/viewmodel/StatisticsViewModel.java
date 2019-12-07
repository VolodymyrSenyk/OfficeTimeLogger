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

public class StatisticsViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final TimeLogsMapper mapper;

    private boolean isFiltered;
    private MutableLiveData<List<TimeLogUi>> logs = new MutableLiveData<>();

    public StatisticsViewModel(TimeLoggerRepository repository, TimeLogsMapper mapper) {
        super("StatisticsVM");
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
        this.repository.getTimeLogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<TimeLogDto>>() {
                    @Override
                    public void onSuccess(List<TimeLogDto> usersDtos) {
                        logs.setValue(mapper.convertToUi(usersDtos));
                    }
                });
    }

    public void cancelFilters() {
        this.isFiltered = false;
        loadTimeLogs();
    }

    public void loadTimeLogs(long start, long end) {
        this.isFiltered = true;
        this.repository.getTimeLogs(start, end)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<TimeLogDto>>() {
                    @Override
                    public void onSuccess(List<TimeLogDto> usersDtos) {
                        logs.setValue(mapper.convertToUi(usersDtos));
                    }
                });
    }

    public void deleteLog(int logId) {
        this.isFiltered = false;
        this.repository.deleteLog(logId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<TimeLogDto>>() {
                    @Override
                    public void onSuccess(List<TimeLogDto> usersDtos) {
                        logs.setValue(mapper.convertToUi(usersDtos));
                    }
                });
    }
}
