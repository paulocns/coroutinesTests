package com.psato.devcamp.presentation.home;

import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.view.View;

import javax.inject.Inject;

public class HomeFragmentViewModel extends ViewModel {

    @Inject
    public HomeFragmentViewModel() {
    }

    public void onMVVMClicked(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), com.psato.devcamp.presentation.MVVM.QueryActivity.class));
    }
}
