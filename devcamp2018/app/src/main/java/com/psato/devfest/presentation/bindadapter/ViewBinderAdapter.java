package com.psato.devfest.presentation.bindadapter;

import android.databinding.BindingAdapter;
import android.view.View;

public class ViewBinderAdapter {

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean visibility){
        if(visibility){
            view.setVisibility(View.VISIBLE);
        }else{
            view.setVisibility(View.GONE);
        }
    }
}
