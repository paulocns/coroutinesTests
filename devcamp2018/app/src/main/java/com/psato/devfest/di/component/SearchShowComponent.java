package com.psato.devfest.di.component;

import com.psato.devfest.di.LoaderScoped;
import com.psato.devfest.presentation.showlist.ShowListVMLoader;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface SearchShowComponent {
    void inject(ShowListVMLoader.ViewModelHolder viewModelHolder);
}
