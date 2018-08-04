package com.psato.devcamp.presentation.bindadapter

import android.databinding.BindingAdapter
import android.view.inputmethod.EditorInfo
import android.widget.EditText

/**
 * Created by psato on 31/10/16.
 */


@BindingAdapter("extractUI")
fun setExtractUi(editText: EditText, extractUi: Boolean) {
    if (!extractUi) {
        val options = editText.imeOptions
        editText.imeOptions = options or EditorInfo.IME_FLAG_NO_EXTRACT_UI
    }
}

