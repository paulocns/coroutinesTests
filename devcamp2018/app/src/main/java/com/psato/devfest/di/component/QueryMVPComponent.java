package com.psato.devfest.di.component;

import com.psato.devfest.di.LoaderScoped;
import com.psato.devfest.presentation.MVP.QueryPresenterLoader;

import dagger.Component;


@LoaderScoped
@Component(dependencies = {ApplicationComponent.class})
public interface QueryMVPComponent {
    void inject(QueryPresenterLoader.PresenterHolder presenterHolder);
}
