package com.example.alexandrun.flowers.ui.orders.list;

import android.view.View;

import com.example.alexandrun.flowers.models.Order;
import com.example.alexandrun.flowers.network.RemoteDataSource;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alexandrun on 4/13/2018.
 */

public class OrdersListPresenter implements OrdersListContract.Presenter {

    private OrdersListContract.View view;
    private RemoteDataSource remoteDataSource;

    public OrdersListPresenter(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void takeView(OrdersListContract.View view) {
        this.view = view;
    }

    @Override
    public void dropView() {
        if (view != null) {
            this.view = null;
        }
    }

    @Override
    public void getOrders() {
        view.setRefreshing(true);
        remoteDataSource.getOrders()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Order>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Order> orders) {
                        view.displayOrders(orders);
                        view.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showMessage("ERROR:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
