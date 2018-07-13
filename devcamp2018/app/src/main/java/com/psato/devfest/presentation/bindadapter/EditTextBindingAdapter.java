package com.psato.devfest.presentation.bindadapter;

import android.databinding.BindingAdapter;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

/**
 * Created by psato on 31/10/16.
 */

public class EditTextBindingAdapter {

    @BindingAdapter("extractUI")
    public static void setExtractUi(EditText editText, boolean extractUi){
        if(!extractUi){
            int options = editText.getImeOptions();
            editText.setImeOptions(options| EditorInfo.IME_FLAG_NO_EXTRACT_UI);
        }
    }
}
