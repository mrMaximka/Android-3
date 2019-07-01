package com.example.daggerhomework.utils;


import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;


public interface BasePresenter<T extends BaseView> extends LifecycleObserver {
    void attach(T view);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void detach();
}
