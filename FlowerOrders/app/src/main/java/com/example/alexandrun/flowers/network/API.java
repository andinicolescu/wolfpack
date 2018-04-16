package com.example.alexandrun.flowers.network;

import com.example.alexandrun.flowers.models.Order;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by alexandrun on 4/13/2018.
 */

public interface API {

    @GET("/flowers")
    Observable<List<Order>> getFlowers();
}
