package com.example.daggerhomework.contracts;


import com.example.daggerhomework.model.data.UserModel;
import com.example.daggerhomework.utils.BasePresenter;
import com.example.daggerhomework.utils.BaseView;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;


public interface UserContract {
    interface View extends BaseView {
        void showUser(UserModel user);
    }

    interface Presenter extends BasePresenter<View> {
        void setUser(String user);

        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        void loadData();
    }
}
