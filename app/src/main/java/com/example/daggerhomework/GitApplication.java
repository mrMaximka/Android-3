package com.example.daggerhomework;


import android.app.Application;

import com.example.daggerhomework.di.AppComponent;
import com.example.daggerhomework.di.DaggerAppComponent;


public class GitApplication extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.create();
    }

    public static AppComponent getComponentInstance(){
        return component;
    }
}
