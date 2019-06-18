package com.example.rumpilstilstkin.lesson4;


import com.example.rumpilstilstkin.lesson4.data.models.GithubUser;
import com.example.rumpilstilstkin.lesson4.data.models.RepsModel;
import com.example.rumpilstilstkin.lesson4.data.rest.NetApiClientInterface;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsPresenter;
import com.example.rumpilstilstkin.lesson4.presenters.home.RepsView;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Flowable;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.hamcrest.MockitoHamcrest.argThat;


@RunWith(MockitoJUnitRunner.class)
public class MockExampleTest {

    private RepsPresenter presenter;

    @Mock private RepsView view;

    @Mock private NetApiClientInterface client;

    @Before
    public void setUp() {
        presenter = new RepsPresenter(client);
    }

    @After
    public void finish(){
        System.out.println("finish");
    }

    @Test
    public void iterator_will_return_hello_world() {
        //подготавливаем
        Iterator i = mock(Iterator.class);
        when(i.next()).thenReturn("Hello").thenReturn("World");
        //выполняем
        String result = i.next() + " " + i.next();
        //сравниваем
        assertEquals("Hello World", result);
    }

    @Test
    public void with_arguments() {
        Comparable<String> c = mock(Comparable.class);
        GithubUser user = new GithubUser();
        //when(c.compareTo(user.getLogin())).thenReturn(1);
        when(c.compareTo(anyString())).thenReturn(1);
        assertEquals(1, c.compareTo(user.getLogin()));
    }

    @Test
    public void with_unspecified_arguments() {
        Comparable c = mock(Comparable.class);
        when(c.compareTo(anyInt())).thenReturn(-1);
        assertEquals(-1, c.compareTo(5));
    }

    @Test(expected = IOException.class)
    public void OutputStreamWriter_rethrows_an_exception_from_OutputStream()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        doThrow(new IOException()).when(mock).close();
        osw.close();
    }

    @Test
    public void OutputStreamWriter_Closes_OutputStream_on_Close()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.close();
        verify(mock).close();
    }

    @Test
    public void OutputStreamWriter_Buffers_And_Forwards_To_OutputStream()
            throws IOException {
        OutputStream mock = mock(OutputStream.class);
        OutputStreamWriter osw = new OutputStreamWriter(mock);
        osw.write('a');
        osw.flush();
        // не можем делать так, потому что мы не знаем,
        // насколько длинным может быть массив
        // verify(mock).write(new byte[]{'a'},0,1);

        BaseMatcher<byte[]> arrayStartingWithA = new BaseMatcher<byte[]>() {
            @Override
            public void describeTo(Description description) {
                // пустота
            }

            // Проверяем, что первый символ - это A
            @Override
            public boolean matches(Object item) {
                byte[] actual = (byte[]) item;
                return actual[0] == 'a';
            }
        };
        // проверяем, что первый символ массива - это A, и что другие два аргумента равны 0 и 1.
        verify(mock).write(argThat(arrayStartingWithA), eq(0), eq(1));
    }

    @Test
    public void testPresenter(){
        RepsModel model = new RepsModel();
        List<RepsModel> list = new ArrayList<>();
        list.add(model);
        when(client.getReps()).thenReturn(Flowable.just(list));

        presenter.attachView(view);
        verify(view).showLoading();
        verify(view).hideLoading();
    }

}
