package com.example.alexandrun.flowers.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.alexandrun.flowers.FlowersApplication;
import com.example.alexandrun.flowers.network.RemoteDataSource;
import com.example.alexandrun.flowers.ui.orders.list.OrdersAdapter;
import com.example.alexandrun.flowers.ui.orders.list.OrdersListContract;
import com.example.alexandrun.flowers.ui.orders.list.OrdersListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alexandrun on 4/13/2018.
 */

@Module(includes = NetworkModule.class)
public class AppModule {
    private final FlowersApplication application;

    public AppModule(FlowersApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    OrdersListContract.Presenter providesMainPresenter(RemoteDataSource remoteDataSource) {
        return new OrdersListPresenter(remoteDataSource);
    }

    @Provides
    OrdersAdapter providesOrdersAdapter() {
        return new OrdersAdapter();
    }
}
