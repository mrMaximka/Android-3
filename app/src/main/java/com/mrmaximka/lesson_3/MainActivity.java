package com.mrmaximka.lesson_3;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import org.reactivestreams.Subscription;

import java.io.File;
import java.io.FileOutputStream;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Button btnConvert;
    Button btnDispose;
    Disposable disposable;

    private final int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setViewSettings();
    }

    private void initView() {
        btnConvert = findViewById(R.id.btn_convert);
        btnDispose = findViewById(R.id.btn_dispose);
    }

    private void setViewSettings() {
        btnConvert.setOnClickListener(v -> getImage());
        btnDispose.setOnClickListener(v -> disposable.dispose());
    }

    private void getImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getFilesDir(), "image.png");
            disposable = Completable.fromAction(() -> {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    FileOutputStream outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableCompletableObserver() {
                @Override
                public void onComplete() {
                    showToast();
                }

                @Override
                public void onError(Throwable e) {
                    showError();
                }
            });

        }
    }

    private void showError() {
        Toast.makeText(this, "Ошибка при конвертации", Toast.LENGTH_LONG).show();
    }

    private void showToast() {
        Toast.makeText(this, "Конвертация успешно завершена", Toast.LENGTH_LONG).show();
    }
}
