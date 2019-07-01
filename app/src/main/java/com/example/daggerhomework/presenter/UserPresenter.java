package com.example.daggerhomework.presenter;


import android.util.Log;

import com.example.daggerhomework.contracts.UserContract;
import com.example.daggerhomework.model.data.UserModel;
import com.example.daggerhomework.model.repository.UserRepository;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class UserPresenter implements UserContract.Presenter, Subscriber<UserModel> {

    UserRepository repository;

    private UserContract.View view;
    private String user = "";

    public UserPresenter(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void attach(UserContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
        Log.d("Dto", "detach");
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(UserModel userModel) {
        view.showUser(userModel);
    }

    @Override
    public void onComplete() {
        view.finishLoading();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        view.showError(e.getLocalizedMessage());
    }

    @Override
    public void loadData() {
        repository.getUser(user).subscribe(this);
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }
}
