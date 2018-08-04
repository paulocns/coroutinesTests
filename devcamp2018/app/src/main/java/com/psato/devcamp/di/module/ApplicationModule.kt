/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.psato.devcamp.di.module

import com.psato.devcamp.data.remote.APIConstants
import com.psato.devcamp.di.component.ViewModelSubComponent
import com.psato.devcamp.infrastructure.DevCampApplication
import com.psato.devcamp.infrastructure.ProjectViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class ApplicationModule(private val application: DevCampApplication) {

    @Provides
    @Singleton
    internal fun provideApplication(): DevCampApplication {
        return application
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(APIConstants.BASE_URL)
                .build()
    }

    @Singleton
    @Provides
    internal fun provideViewModelFactory(
            viewModelSubComponent: ViewModelSubComponent.Builder): ProjectViewModelFactory {

        return ProjectViewModelFactory(viewModelSubComponent.build())
    }
}
