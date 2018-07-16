package com.psato.devcamp.di.component;

import com.psato.devcamp.di.LoaderScoped;
import com.psato.devcamp.presentation.base.BaseFragment;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface BaseFragmentComponent {
    void inject(BaseFragment baseFragment);
}
