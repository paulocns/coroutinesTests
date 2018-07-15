package com.psato.devcamp.di.component;

import com.psato.devcamp.di.LoaderScoped;
import com.psato.devcamp.presentation.MVVM.QueryVMLoader;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface QueryComponent {
    void inject(QueryVMLoader.ViewModelHolder viewModelHolder);
}
