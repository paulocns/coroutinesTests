package com.psato.devcamp.di.module

import com.psato.devcamp.data.repository.resource.ResourceRepository
import com.psato.devcamp.data.repository.resource.ResourceRepositoryImpl
import com.psato.devcamp.infrastructure.DevCampApplication

import dagger.Module
import dagger.Provides

@Module
class ResourceRepositoryModule {

    @Provides
    internal fun provideResourceRepository(application: DevCampApplication): ResourceRepository {
        return ResourceRepositoryImpl(application)
    }
}
