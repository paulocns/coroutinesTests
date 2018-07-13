package com.psato.devfest.di.module;

import com.psato.devfest.data.remote.TraktAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by athila on 31/05/16.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    TraktAPI provideUserApi(Retrofit retrofit) {
        return retrofit.create(TraktAPI.class);
    }
}
