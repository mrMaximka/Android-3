package com.example.daggerhomework.presenter;


import com.example.daggerhomework.contracts.RepoListContract;
import com.example.daggerhomework.model.data.RepoModel;
import com.example.daggerhomework.model.repository.RepoRepository;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.List;


public class RepoListPresenter implements RepoListContract.Presenter, Subscriber<List<RepoModel>> {

    RepoRepository repository;

    private RepoListContract.View view;

    public RepoListPresenter(RepoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void attach(RepoListContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @Override
    public void onSubscribe(Subscription s) {
        s.request(Long.MAX_VALUE);
    }

    @Override
    public void onNext(List<RepoModel> repoModels) {
        if(view != null) view.showRepos(repoModels);
    }

    @Override
    public void onComplete() {
        if(view != null)  view.finishLoading();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(view != null)  view.showError(e.getLocalizedMessage());
    }

    @Override
    public void loadData() {
        repository.getReps().subscribe(this);
    }
}
