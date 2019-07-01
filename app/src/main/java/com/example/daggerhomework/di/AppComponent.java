package com.example.daggerhomework.di;


import com.example.daggerhomework.view.repo.details.RepoDetailsActivity;
import com.example.daggerhomework.view.repo.list.RepoListActivity;
import com.example.daggerhomework.view.user.UserDetailsActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetModule.class, PresenterModule.class})
public interface AppComponent {

    void inject(RepoDetailsActivity activity);

    void inject(RepoListActivity activity);

    void inject(UserDetailsActivity activity);
}
