package com.mrmaximka.lesson2;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Task1 extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button btnDispose;
    Button btnSubscribe;

    Observer<String> observer;
    Observable observable;
    Disposable disposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intiView();
        createObservable();
        setViewSettings();
    }

    private void intiView() {
        textView = findViewById(R.id.text_view);
        editText = findViewById(R.id.edit_text);
        btnDispose = findViewById(R.id.btn_dispose);
        btnSubscribe = findViewById(R.id.btn_subscribe);
    }

    private void createObservable() {
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {disposable = d;}

            @Override
            public void onNext(String s) {textView.setText(s);}

            @Override
            public void onError(Throwable e) {}

            @Override
            public void onComplete() {}
        };

        observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(final ObservableEmitter emitter) throws Exception {
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {emitter.onNext(s.toString());}
                });
            }
        });

        observable.subscribe(observer);
    }

    private void setViewSettings() {
        btnDispose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disposable.dispose();
            }
        });

        btnSubscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                observable.subscribe(observer);
            }
        });
    }
}
