package com.mrmaximka.lesson2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class Task2 extends AppCompatActivity {

    TextView tvPlus;
    TextView tvMinus;
    EditText etA;
    EditText etB;

    long paramA;
    long paramB;

    Observer<Boolean> observerMinus;
    Observer<Boolean> observerPlus;
    Observable observableA;
    Observable observableB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);

        intiView();
        createObservable();
        setViewSettings();
    }

    private void intiView() {
        tvPlus = findViewById(R.id.tv_plus);
        tvMinus = findViewById(R.id.tv_minus);
        etA = findViewById(R.id.et_a);
        etB = findViewById(R.id.et_b);
    }

    private void setViewSettings() {
        paramA = 0;
        paramB = 0;
    }

    private void createObservable() {

        observerPlus = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(Boolean aBoolean) {tvPlus.setText(String.format("%d", paramA + paramB));}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };

        observerMinus = new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {}

            @Override
            public void onNext(Boolean integer) {tvMinus.setText(String.format("%d", paramA - paramB));}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };

        observableA = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(final ObservableEmitter emitter) throws Exception {
                etA.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().isEmpty()){
                            paramA = 0;
                        }else {
                            paramA = Integer.parseInt(s.toString());
                        }
                        emitter.onNext(true);
                    }
                });
            }
        });

        observableB = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(final ObservableEmitter emitter) throws Exception {
                etB.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.toString().isEmpty()){
                            paramB = 0;
                        }else {
                            paramB = Integer.parseInt(s.toString());
                        }
                        emitter.onNext(true);
                    }
                });
            }
        });

        PublishSubject<Boolean> subject = PublishSubject.create();
        observableA.subscribe(subject);
        observableB.subscribe(subject);
        subject.subscribe(observerPlus);
        subject.subscribe(observerMinus);
    }

}
