package com.example.daggerhomework.contracts;


import com.example.daggerhomework.model.data.RepoDetailsModel;
import com.example.daggerhomework.utils.BasePresenter;
import com.example.daggerhomework.utils.BaseView;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;


public interface RepoDetailsContract {
    interface View extends BaseView {
        void showRepo(RepoDetailsModel model);
    }

    interface Presenter extends BasePresenter<View> {
        void setData(String user, String repo);

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void updateData();
    }
}