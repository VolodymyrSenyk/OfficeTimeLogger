package com.senyk.volodymyr.officetimelogger.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.senyk.volodymyr.officetimelogger.helpers.ResourcesProvider;
import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;
import com.senyk.volodymyr.officetimelogger.models.dto.CredentialsDto;
import com.senyk.volodymyr.officetimelogger.repository.TimeLoggerRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AuthorizationViewModel extends BaseViewModel {
    private final TimeLoggerRepository repository;
    private final ResourcesProvider resourcesProvider;

    private MutableLiveData<Boolean> isAuthorized = new SingleEventLiveData<>();

    public AuthorizationViewModel(TimeLoggerRepository repository, ResourcesProvider resourcesProvider) {
        super("AuthorizationVM");
        this.repository = repository;
        this.resourcesProvider = resourcesProvider;
    }

    public LiveData<Boolean> isAuthorized() {
        return this.isAuthorized;
    }

    public void logIn(String number, String password) {
        this.repository.logIn(new CredentialsDto(Integer.parseInt(number), Integer.parseInt(password)))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MainCompletableObserver() {
                    @Override
                    public void onComplete() {
                        isAuthorized.setValue(true);
                    }

                    @Override
                    public void onError(Throwable e) {
                        message.setValue(resourcesProvider.getErrorMessage(e));
                    }
                });
    }
}
