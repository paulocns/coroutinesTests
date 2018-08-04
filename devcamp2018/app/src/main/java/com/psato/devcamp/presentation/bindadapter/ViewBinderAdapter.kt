package com.psato.devcamp.presentation.bindadapter

import android.databinding.BindingAdapter
import android.view.View

@BindingAdapter("android:visibility")
fun setVisibility(view: View, visibility: Boolean) {
    if (visibility) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}

