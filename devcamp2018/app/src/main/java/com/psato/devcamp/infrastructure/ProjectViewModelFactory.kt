package com.psato.devcamp.infrastructure

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.util.ArrayMap

import com.psato.devcamp.di.component.ViewModelSubComponent
import com.psato.devcamp.presentation.MVVM.QueryViewModelArc
import com.psato.devcamp.presentation.home.HomeFragmentViewModel
import java.util.concurrent.Callable

import javax.inject.Inject
import javax.inject.Singleton

class ProjectViewModelFactory(viewModelSubComponent: ViewModelSubComponent) : ViewModelProvider.Factory {
    private val creators: ArrayMap<Class<*>,() ->ViewModel> = ArrayMap()

    init {
        creators[HomeFragmentViewModel::class.java] = { viewModelSubComponent.homeFragmentViewModel() }
        creators[QueryViewModelArc::class.java] = { viewModelSubComponent.queryViewModelArc() }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: (() ->ViewModel)? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
        try {
            return creator.invoke()as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}