package com.example.daggerhomework.di;


import com.example.daggerhomework.model.repository.RepoRepository;
import com.example.daggerhomework.model.repository.UserRepository;
import com.example.daggerhomework.presenter.RepoDetailsPresenter;
import com.example.daggerhomework.presenter.RepoListPresenter;
import com.example.daggerhomework.presenter.UserPresenter;

import dagger.Module;
import dagger.Provides;


@Module
public class PresenterModule {

    @Provides
    RepoDetailsPresenter getRepoDetailsPresenter(RepoRepository repository) {
        return new RepoDetailsPresenter(repository);
    }

    @Provides
    RepoListPresenter getRepoListPresenter(RepoRepository repository) {
        return new RepoListPresenter(repository);
    }

    @Provides
    UserPresenter getUserPresenter(UserRepository repository) {
        return new UserPresenter(repository);
    }
}
