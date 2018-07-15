package com.psato.devcamp.di.module;

import com.psato.devcamp.data.repository.resource.ResourceRepository;
import com.psato.devcamp.data.repository.resource.ResourceRepositoryImpl;
import com.psato.devcamp.infrastructure.DevCampApplication;

import dagger.Module;
import dagger.Provides;

@Module
public class ResourceRepositoryModule {

    @Provides
    ResourceRepository provideResourceRepository(DevCampApplication application) {
        return new ResourceRepositoryImpl(application);
    }
}
