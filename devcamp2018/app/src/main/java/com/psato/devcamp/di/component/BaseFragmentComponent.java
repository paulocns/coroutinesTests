package com.psato.devcamp.di.component;

import com.psato.devcamp.di.FragmentScoped;
import com.psato.devcamp.presentation.base.BaseFragment;

import dagger.Component;


@FragmentScoped
@Component(dependencies = {ApplicationComponent.class})
public interface BaseFragmentComponent {
    void inject(BaseFragment baseFragment);
}
