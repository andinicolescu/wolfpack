package com.example.alexandrun.flowers.network;

import com.example.alexandrun.flowers.models.Order;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by alexandrun on 4/13/2018.
 */

public class RemoteDataSource {

    private API api;

    public RemoteDataSource(API api) {
        this.api = api;
    }

    public Observable<List<Order>> getOrders() {
        return api.getFlowers();
    }
}
