package com.example.daggerhomework.contracts;


import com.example.daggerhomework.model.data.RepoModel;
import com.example.daggerhomework.utils.BasePresenter;
import com.example.daggerhomework.utils.BaseView;

import java.util.List;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;


public interface RepoListContract {
    interface View extends BaseView {
        void showRepos(List<RepoModel> list);
    }

    interface Presenter extends BasePresenter<View> {

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void loadData();
    }
}
