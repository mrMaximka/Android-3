package com.mrmaximka.a3_lesson1.moxy;

import com.arellomobile.mvp.MvpView;

public interface IView extends MvpView {
    void setSeconds(int value);
    void setMinute(int value);
    void setHours(int value);
}
