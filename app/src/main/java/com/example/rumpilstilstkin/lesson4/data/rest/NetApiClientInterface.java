package com.example.rumpilstilstkin.lesson4.data.rest;


import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;


public interface NetApiClientInterface {
    Observable<GithubUser> getUser(String user);
    Flowable<List<RepsModel>> getReps();
}
