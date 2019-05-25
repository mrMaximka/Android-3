package com.mrmaximka.a3_lesson1.mvp;

public class Presenter {

    private final int INC_VALUE = 1;
    private final int SEC_IDX= 0;
    private final int MIN_IDX= 1;
    private final int HOUR_IDX= 2;

    private IModel model;
    private IView view;

    public Presenter(IView view) {
        this.model = new Model();
        this.view = view;
    }

    private int calcNewValue(int index){
        return model.getElementValue(index) + INC_VALUE;
    }

    public void incSec(){
        int newValue = calcNewValue(SEC_IDX);
        model.setElementValue(SEC_IDX, newValue);
        view.setSeconds(newValue);
    }

    public void incMin(){
        int newValue = calcNewValue(MIN_IDX);
        model.setElementValue(MIN_IDX, newValue);
        view.setMinute(newValue);
    }

    public void incHours(){
        int newValue = calcNewValue(HOUR_IDX);
        model.setElementValue(HOUR_IDX, newValue);
        view.setHours(newValue);
    }
}
