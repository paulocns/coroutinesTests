package com.psato.devcamp.presentation.home

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.view.View

import javax.inject.Inject

class HomeFragmentViewModel @Inject
constructor() : ViewModel() {

    fun onMVVMClicked(view: View) {
        view.context.startActivity(Intent(view.context, com.psato.devcamp.presentation.MVVM.QueryActivity::class.java))
    }
}
