package com.example.alexandrun.flowers;

import android.app.Application;

import com.example.alexandrun.flowers.dagger.AppComponent;
import com.example.alexandrun.flowers.dagger.AppModule;
import com.example.alexandrun.flowers.dagger.DaggerAppComponent;

/**
 * Created by alexandrun on 4/13/2018.
 */

public class FlowersApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
