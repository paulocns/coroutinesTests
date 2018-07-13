package com.psato.devfest.di.component;

import com.psato.devfest.di.LoaderScoped;
import com.psato.devfest.presentation.MVVM.QueryVMLoader;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface QueryComponent {
    void inject(QueryVMLoader.ViewModelHolder viewModelHolder);
}
