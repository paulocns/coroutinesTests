package com.psato.devcamp.presentation.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.psato.devcamp.di.component.DaggerBaseFragmentComponent;
import com.psato.devcamp.infrastructure.DevCampApplication;
import com.psato.devcamp.infrastructure.ProjectViewModelFactory;

import javax.inject.Inject;

/**
 * Created by psato on 29/10/16.
 */

public abstract class BaseFragment extends Fragment {

    @Inject
    ProjectViewModelFactory viewModelFactory;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DevCampApplication app = (DevCampApplication) getActivity().getApplication();
        DaggerBaseFragmentComponent.builder().applicationComponent(app.getApplicationComponent())
                .build().inject(this);
    }

    protected ProjectViewModelFactory getViewModelFactory(){
        return viewModelFactory;
    }


}
