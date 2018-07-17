/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.psato.devcamp.di.module;

import android.arch.lifecycle.ViewModelProvider;

import com.psato.devcamp.data.remote.APIConstants;
import com.psato.devcamp.di.component.ViewModelSubComponent;
import com.psato.devcamp.infrastructure.DevCampApplication;
import com.psato.devcamp.data.remote.APIConstants;
import com.psato.devcamp.infrastructure.DevCampApplication;
import com.psato.devcamp.infrastructure.ProjectViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(subcomponents = {ViewModelSubComponent.class})
public class ApplicationModule {
    private final DevCampApplication mApplication;

    public ApplicationModule(DevCampApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    DevCampApplication provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIConstants.BASE_URL)
                .build();
    }

    @Singleton
    @Provides
    ProjectViewModelFactory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new ProjectViewModelFactory(viewModelSubComponent.build());
    }
}
