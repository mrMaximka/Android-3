package com.example.rumpilstilstkin.lesson4.data;


import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Endpoints {

    @GET("/users/{user}")
    Observable<GithubUser> getUser(
            @Path("user") String user
    );

    @GET("/repositories")
    Flowable<List<RepsModel>> getRepos();
}
