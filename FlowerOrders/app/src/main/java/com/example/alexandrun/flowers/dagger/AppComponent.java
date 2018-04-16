package com.example.alexandrun.flowers.dagger;

import com.example.alexandrun.flowers.ui.main.MainActivity;
import com.example.alexandrun.flowers.ui.orders.list.OrdersListFragment;
import com.example.alexandrun.flowers.ui.orders.list.OrdersListPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alexandrun on 4/13/2018.
 */

@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(MainActivity mainActivity);

    void inject(OrdersListPresenter mainPresenter);

    void inject(OrdersListFragment ordersListFragment);
}
