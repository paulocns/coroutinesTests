package com.psato.devcamp.di.component;

import com.psato.devcamp.di.LoaderScoped;
import com.psato.devcamp.presentation.home.HomeVMLoader;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface HomeComponent {
    void inject(HomeVMLoader.ViewModelHolder viewModelHolder);
}
