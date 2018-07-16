package com.psato.devcamp.di.component;

import com.psato.devcamp.presentation.MVVM.QueryViewModelArc;
import com.psato.devcamp.presentation.home.HomeFragmentViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    HomeFragmentViewModel homeFragmentViewModel();
    QueryViewModelArc queryViewModelArc();
}
