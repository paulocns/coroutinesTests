package com.psato.devcamp.di.component;

import com.psato.devcamp.di.LoaderScoped;
import com.psato.devcamp.presentation.showlist.ShowListVMLoader;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface SearchShowComponent {
    void inject(ShowListVMLoader.ViewModelHolder viewModelHolder);
}
