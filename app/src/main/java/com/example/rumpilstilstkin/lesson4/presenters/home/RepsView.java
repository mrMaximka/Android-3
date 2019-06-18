package com.example.rumpilstilstkin.lesson4.presenters.home;

import com.arellomobile.mvp.MvpView;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;

import java.util.List;

public interface RepsView extends MvpView {

    void showError(Throwable e);

    void showLoading();

    void hideLoading();
}
