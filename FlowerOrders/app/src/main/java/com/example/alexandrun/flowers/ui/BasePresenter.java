package com.example.alexandrun.flowers.ui;

/**
 * Created by alexandrun on 4/13/2018.
 */

public interface BasePresenter<T> {
    void takeView(T view);
    void dropView();
}
