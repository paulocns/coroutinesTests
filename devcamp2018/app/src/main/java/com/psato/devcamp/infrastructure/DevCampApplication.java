package com.psato.devcamp.infrastructure;

import android.app.Application;

import com.psato.devcamp.di.component.ApplicationComponent;
import com.psato.devcamp.di.module.ApplicationModule;
import com.psato.devcamp.di.component.DaggerApplicationComponent;

/**
 * Created by psato on 29/10/16.
 */

public class DevCampApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
