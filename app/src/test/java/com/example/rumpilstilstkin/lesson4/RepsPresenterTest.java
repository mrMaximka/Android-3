package com.example.rumpilstilstkin.lesson4;


import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClientInterface;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsPresenter;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsView;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsView$$State;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepsPresenterTest {

    private RepsPresenter presenter;
    @Mock
    RepsView$$State repsViewState;
    @Mock
    private RepsView view;

    @Mock private NetApiClientInterface client;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new RepsPresenter(client);
    }

    @Test
    public void testShowList(){
        RepsModel model = new RepsModel();
        List<RepsModel> list = new ArrayList<>();
        list.add(model);
        when(client.getReps()).thenReturn(Flowable.just(list));

        presenter.attachView(view);
        verify(view).showLoading();
        verify(view).hideLoading();
    }

    @Test
    public void testShowError(){
        Exception error = new IOException();
        when(client.getReps()).thenReturn(Flowable.fromCallable(() -> { throw error; }));
        presenter.attachView(view);
        presenter.setViewState(repsViewState);
        verify(view).showLoading();
        verify(view).showError(error);
        verify(view).hideLoading();
        verifyNoMoreInteractions(view);
    }

}
