package com.mrmaximka.a3_lesson1.moxy;


import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class Presenter extends MvpPresenter<IView> {

    private final int INC_VALUE = 1;
    private final int SEC_IDX= 0;
    private final int MIN_IDX= 1;
    private final int HOUR_IDX= 2;

    private IModel model;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        model = new Model();
    }

    @Override
    public void attachView(IView view) {
        super.attachView(view);
    }

    private int calcNewValue(int index){
        return model.getElementValue(index) + INC_VALUE;
    }

    void incSec(){
        int newValue = calcNewValue(SEC_IDX);
        model.setElementValue(SEC_IDX, newValue);
        getViewState().setSeconds(newValue);

    }

    void incMin(){
        int newValue = calcNewValue(MIN_IDX);
        model.setElementValue(MIN_IDX, newValue);
        getViewState().setMinute(newValue);
    }

    void incHours(){
        int newValue = calcNewValue(HOUR_IDX);
        model.setElementValue(HOUR_IDX, newValue);
        getViewState().setHours(newValue);
    }
}
