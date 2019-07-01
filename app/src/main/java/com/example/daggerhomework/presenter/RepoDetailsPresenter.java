package com.example.daggerhomework.presenter;

import com.example.daggerhomework.contracts.RepoDetailsContract;
import com.example.daggerhomework.model.data.RepoDetailsModel;
import com.example.daggerhomework.model.repository.RepoRepository;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class RepoDetailsPresenter implements RepoDetailsContract.Presenter, Subscriber<RepoDetailsModel> {

    RepoRepository repository;

    private RepoDetailsContract.View view;
    private String user;
    private String repo;

    public RepoDetailsPresenter(RepoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void attach(RepoDetailsContract.View view) {
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
    public void onNext(RepoDetailsModel repoDetailsModel) {
        if(view != null) view.showRepo(repoDetailsModel);
    }

    @Override
    public void onComplete() {
        if(view != null) view.finishLoading();
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if(view != null) view.showError(e.getLocalizedMessage());
    }

    @Override
    public void setData(String user, String repo) {
        this.user = user;
        this.repo = repo;
    }

    @Override
    public void updateData() {
        repository.getRepoByNameAndUser(user, repo).subscribe(this);
    }
}
