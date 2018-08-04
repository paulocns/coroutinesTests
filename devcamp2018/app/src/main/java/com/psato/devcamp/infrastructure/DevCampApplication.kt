package com.psato.devcamp.infrastructure

import android.app.Application
import com.psato.devcamp.di.component.ApplicationComponent
import com.psato.devcamp.di.component.DaggerApplicationComponent
import com.psato.devcamp.di.module.ApplicationModule

/**
 * Created by psato on 29/10/16.
 */

class DevCampApplication : Application() {
    var applicationComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
    }

    private fun initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
