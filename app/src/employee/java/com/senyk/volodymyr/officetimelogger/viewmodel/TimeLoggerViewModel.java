package com.senyk.volodymyr.officetimelogger.viewmodel;

import android.util.Pair;

import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.models.dto.TimeLogDto;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TimeLoggerViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final ResourcesProvider resourcesProvider;

    public TimeLoggerViewModel(TimeLoggerRepository repository, ResourcesProvider resourcesProvider) {
        super("TimeLoggerVM");
        this.repository = repository;
        this.resourcesProvider = resourcesProvider;
    }

    public void addTimeLog(TimeLogDto log) {
        this.repository.logTime(log)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        message.setValue(resourcesProvider.getSuccessfullyLoggedMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        message.setValue(resourcesProvider.getErrorMessage(e));
                    }
                });
    }

    public void changePassword(Pair<String, String> passwords) {
        this.repository.changePassword(passwords)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        message.setValue(resourcesProvider.getPasswordSuccessfullyChangedMessage());
                    }

                    @Override
                    public void onError(Throwable e) {
                        message.setValue(resourcesProvider.getErrorMessage(e));
                    }
                });
    }
}
