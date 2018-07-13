package com.psato.devfest.presentation.home;

import android.content.Intent;
import android.view.View;

import com.psato.devfest.presentation.MVP.QueryActivity;
import com.psato.devfest.presentation.base.BaseViewModel;

import javax.inject.Inject;

public class HomeFragmentViewModel extends BaseViewModel {

    @Inject
    public HomeFragmentViewModel() {
    }

    @Override
    public void start() {
        //nothing to do
    }

    @Override
    public void stop() {
        //nothing to do
    }

    public void onMVPClicked(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), QueryActivity.class));

    }

    public void onMVVMClicked(View view) {
        view.getContext().startActivity(new Intent(view.getContext(), com.psato.devfest.presentation.MVVM.QueryActivity.class));
    }
}
