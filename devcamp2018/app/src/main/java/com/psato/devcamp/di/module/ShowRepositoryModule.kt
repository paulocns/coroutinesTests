package com.psato.devcamp.di.module

import com.psato.devcamp.data.remote.TraktAPI
import com.psato.devcamp.data.repository.show.ShowRepository
import com.psato.devcamp.data.repository.show.ShowRepositoryImpl

import dagger.Module
import dagger.Provides

@Module
class ShowRepositoryModule {

    @Provides
    internal fun provideUserRepository(traktAPI: TraktAPI): ShowRepository {
        return ShowRepositoryImpl(traktAPI)
    }
}
