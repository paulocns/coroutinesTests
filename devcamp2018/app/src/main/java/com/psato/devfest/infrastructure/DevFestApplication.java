package com.psato.devfest.infrastructure;

import android.app.Application;

import com.psato.devfest.di.component.ApplicationComponent;
import com.psato.devfest.di.component.DaggerApplicationComponent;
import com.psato.devfest.di.module.ApplicationModule;

/**
 * Created by psato on 29/10/16.
 */

public class DevFestApplication extends Application {
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
