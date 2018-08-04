package com.psato.devcamp.presentation.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

import com.psato.devcamp.di.component.DaggerBaseFragmentComponent
import com.psato.devcamp.infrastructure.DevCampApplication
import com.psato.devcamp.infrastructure.ProjectViewModelFactory

import javax.inject.Inject

/**
 * Created by psato on 29/10/16.
 */

abstract class BaseFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ProjectViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = activity!!.application as DevCampApplication
        DaggerBaseFragmentComponent.builder().applicationComponent(app.applicationComponent)
                .build().inject(this)
    }

}
