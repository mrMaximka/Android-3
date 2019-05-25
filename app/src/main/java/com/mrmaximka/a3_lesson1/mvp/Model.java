package com.mrmaximka.a3_lesson1.mvp;

import java.util.ArrayList;

public class Model implements IModel{
    
    private ArrayList<Integer> list;
    private final int MAX_ITEMS = 3;
    
    public Model() {
        list = new ArrayList<>(MAX_ITEMS);
        for (int i = 0; i < MAX_ITEMS; i++) list.add(0);
    }


    @Override
    public int getElementValue(int index) {
        return list.get(index);
    }

    @Override
    public void setElementValue(int index, int value) {
        list.set(index, value);
    }
}
