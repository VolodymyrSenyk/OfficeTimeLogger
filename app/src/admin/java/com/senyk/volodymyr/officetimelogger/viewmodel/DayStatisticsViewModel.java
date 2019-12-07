package com.senyk.volodymyr.officetimelogger.viewmodel;

import android.util.Pair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.mappers.dtoui.PairsMapper;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.models.dto.UserDto;
import com.senyk.volodymyr.officetimelogger.models.ui.TimeLogUi;
import com.senyk.volodymyr.officetimelogger.models.ui.UserUi;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

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

    public void loadUsersAndLogs(long start, long end) {
        this.repository.getLogByDay(start, end)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainSingleObserver<List<Pair<UserDto, TimeLogDto>>>() {
                    @Override
                    public void onSuccess(List<Pair<UserDto, TimeLogDto>> dtos) {
                        logs.setValue(pairsMapper.convertToUi(dtos));
                    }
                });
    }
}
