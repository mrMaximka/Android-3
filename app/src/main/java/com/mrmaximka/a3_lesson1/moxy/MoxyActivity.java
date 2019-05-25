package com.mrmaximka.a3_lesson1.moxy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.mrmaximka.a3_lesson1.R;

public class MoxyActivity extends MvpAppCompatActivity
        implements IView, View.OnClickListener{

    private Button btnCounter1;
    private Button btnCounter2;
    private Button btnCounter3;

    @InjectPresenter
    Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setViewSettings();
    }

    private void initView() {
        btnCounter1 = findViewById(R.id.btnCounter1);
        btnCounter2 = findViewById(R.id.btnCounter2);
        btnCounter3 = findViewById(R.id.btnCounter3);
    }

    private void setViewSettings() {
        btnCounter1.setOnClickListener(this);
        btnCounter2.setOnClickListener(this);
        btnCounter3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCounter1:
                presenter.incSec();
                break;

            case R.id.btnCounter2:
                presenter.incMin();
                break;

            case R.id.btnCounter3:
                presenter.incHours();
                break;

            default:
                break;
        }
    }

    @Override
    public void setSeconds(int value) {
        btnCounter1.setText(String.format(getString(R.string.btnText), value));
    }

    @Override
    public void setMinute(int value) {
        btnCounter2.setText(String.format(getString(R.string.btnText), value));
    }

    @Override
    public void setHours(int value) {
        btnCounter3.setText(String.format(getString(R.string.btnText), value));
    }
}
