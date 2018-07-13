package com.psato.devfest.di.component;

import com.psato.devfest.data.repository.resource.ResourceRepository;
import com.psato.devfest.data.repository.show.ShowRepository;
import com.psato.devfest.di.module.ApplicationModule;
import com.psato.devfest.di.module.NetworkModule;
import com.psato.devfest.di.module.ResourceRepositoryModule;
import com.psato.devfest.di.module.ShowRepositoryModule;
import com.psato.devfest.infrastructure.DevFestApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by psato on 29/10/16.
 */

@Singleton
@Component(modules = {
        ApplicationModule.class,
        NetworkModule.class,
        ShowRepositoryModule.class,
        ResourceRepositoryModule.class
})
public interface ApplicationComponent {
    // expose to sub graphs

    DevFestApplication application();

    ShowRepository showRepository();

    ResourceRepository resourceRepository();
}
