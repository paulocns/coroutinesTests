package com.psato.devcamp.di.component

import com.psato.devcamp.di.FragmentScoped
import com.psato.devcamp.presentation.base.BaseFragment

import dagger.Component


@FragmentScoped
@Component(dependencies = arrayOf(ApplicationComponent::class))
interface BaseFragmentComponent {
    fun inject(baseFragment: BaseFragment)
}
