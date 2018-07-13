package com.psato.devfest.di.component;

import com.psato.devfest.di.LoaderScoped;
import com.psato.devfest.presentation.home.HomeVMLoader;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface HomeComponent {
    void inject(HomeVMLoader.ViewModelHolder viewModelHolder);
}
