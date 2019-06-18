package com.example.rumpilstilstkin.lesson4.ui.home;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.PresenterType;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.rumpilstilstkin.lesson4.R;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClient;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsPresenter;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsView;
import com.example.rumpilstilstkin.lesson4.presenters.home.UserPresenter;
import com.example.rumpilstilstkin.lesson4.presenters.home.UserView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends MvpAppCompatActivity
        implements UserView, RepsView {

    @InjectPresenter
    UserPresenter presenter;

    @InjectPresenter(type = PresenterType.LOCAL)
    RepsPresenter repsPresenter;

    @ProvidePresenter(type = PresenterType.LOCAL)
    RepsPresenter providePresenter() {
        return new RepsPresenter(NetApiClient.getInstance());
    }

    @BindView(R.id.avatar) ImageView imageView;
    @BindView(R.id.username) TextView nameView;
    @BindView(R.id.loadingView) ProgressBar progress;
    @BindView(R.id.contentView) View content;

    @OnClick(R.id.button)
    public void submit(View view) {
        presenter.loadDate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void showImage(String imageUrl) {
      /*  Glide.with(this)
                .load(imageUrl)
                .into(imageView);*/

        Picasso.get()
                .load(imageUrl)
                .into(imageView);
    }

    @Override
    public void showName(String name) {
        nameView.setText(name);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progress.setVisibility(View.VISIBLE);
        content.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progress.setVisibility(View.GONE);
        content.setVisibility(View.VISIBLE);
    }
}
