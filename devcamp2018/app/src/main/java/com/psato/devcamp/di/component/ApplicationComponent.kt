package com.psato.devcamp.di.component

import com.psato.devcamp.data.repository.resource.ResourceRepository
import com.psato.devcamp.data.repository.show.ShowRepository
import com.psato.devcamp.di.module.ApplicationModule
import com.psato.devcamp.di.module.NetworkModule
import com.psato.devcamp.di.module.ResourceRepositoryModule
import com.psato.devcamp.di.module.ShowRepositoryModule
import com.psato.devcamp.infrastructure.DevCampApplication
import com.psato.devcamp.infrastructure.ProjectViewModelFactory

import javax.inject.Singleton

import dagger.Component

/**
 * Created by psato on 29/10/16.
 */

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class, ShowRepositoryModule::class, ResourceRepositoryModule::class))
interface ApplicationComponent {
    // expose to sub graphs

    fun application(): DevCampApplication

    fun showRepository(): ShowRepository

    fun resourceRepository(): ResourceRepository

    fun viewModelFactory(): ProjectViewModelFactory
}
