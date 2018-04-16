package com.example.alexandrun.flowers.dagger;

import com.example.alexandrun.flowers.BuildConfig;
import com.example.alexandrun.flowers.network.API;
import com.example.alexandrun.flowers.network.RemoteDataSource;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alexandrun on 4/13/2018.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Retrofit provideCall() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    API provideAPI(Retrofit retrofit) {
        return retrofit.create(API.class);
    }

    @Provides
    @Singleton
    RemoteDataSource provideRemoteDataSource(API api) {
        return new RemoteDataSource(api);
    }
}
