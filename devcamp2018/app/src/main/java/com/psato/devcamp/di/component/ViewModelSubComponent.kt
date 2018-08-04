package com.psato.devcamp.di.component

import com.psato.devcamp.presentation.MVVM.QueryViewModelArc
import com.psato.devcamp.presentation.home.HomeFragmentViewModel

import dagger.Subcomponent

@Subcomponent
interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        fun build(): ViewModelSubComponent
    }

    fun homeFragmentViewModel(): HomeFragmentViewModel
    fun queryViewModelArc(): QueryViewModelArc
}
