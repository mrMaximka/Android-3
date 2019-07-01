package com.example.daggerhomework.di;

import com.example.daggerhomework.model.net.Endpoints;
import com.example.daggerhomework.model.net.ServiceGenerator;
import com.example.daggerhomework.model.repository.RepoRepository;
import com.example.daggerhomework.model.repository.UserRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    @Provides
    @Singleton
    Endpoints getUserEndpoints() {
        return new ServiceGenerator().createService(Endpoints.class);
    }

    @Provides
    RepoRepository getRepoRepository(Endpoints endpoints) {
        return new RepoRepository(endpoints);
    }

    @Provides
    UserRepository getUserRepository(Endpoints endpoints) {
        return new UserRepository(endpoints);
    }
}
