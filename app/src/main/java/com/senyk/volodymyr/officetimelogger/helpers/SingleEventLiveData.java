package com.senyk.volodymyr.officetimelogger.helpers;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.concurrent.atomic.AtomicBoolean;

public class SingleEventLiveData<T> extends MutableLiveData<T> {
    private AtomicBoolean pending = new AtomicBoolean(false);

    @Override
    public void observe(@NonNull LifecycleOwner owner, @NonNull final Observer<? super T> observer) {
        super.observe(owner, data -> {
            if (SingleEventLiveData.this.pending.compareAndSet(true, false)) {
                observer.onChanged(data);
            }
        });

    }

    @Override
    public void setValue(T value) {
        this.pending.set(true);
        super.setValue(value);
    }
}
