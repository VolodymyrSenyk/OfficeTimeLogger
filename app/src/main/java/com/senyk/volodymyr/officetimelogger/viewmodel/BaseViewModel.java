package com.senyk.volodymyr.officetimelogger.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.senyk.volodymyr.officetimelogger.helpers.SingleEventLiveData;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {
    private static String TAG = "BaseVM";
    private final CompositeDisposable compositeDisposable;
    protected SingleEventLiveData<String> message = new SingleEventLiveData<>();

    public LiveData<String> getMessage() {
        return this.message;
    }

    public BaseViewModel(String tag) {
        TAG = tag;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onCleared() {
        this.compositeDisposable.dispose();
    }

    protected abstract class MainSingleObserver<T> implements SingleObserver<T> {
        @Override
        public void onSubscribe(Disposable disposable) {
            compositeDisposable.add(disposable);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "An error occurred: " + e.getMessage());
        }
    }

    protected abstract class MainCompletableObserver implements CompletableObserver {
        @Override
        public void onSubscribe(Disposable disposable) {
            compositeDisposable.add(disposable);
        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, "An error occurred: " + e.getMessage());
        }
    }
}
