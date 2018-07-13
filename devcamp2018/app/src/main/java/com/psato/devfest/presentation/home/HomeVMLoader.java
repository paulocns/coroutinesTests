package com.psato.devfest.presentation.home;

import android.content.Context;

import com.psato.devfest.di.component.ApplicationComponent;
import com.psato.devfest.di.component.DaggerHomeComponent;
import com.psato.devfest.presentation.base.BaseViewModel;
import com.psato.devfest.presentation.base.ViewModelLoader;

import javax.inject.Inject;

/**
 * Created by psato on 29/10/16.
 */

public class HomeVMLoader extends ViewModelLoader {
    private ApplicationComponent mApplicationComponent;

    public HomeVMLoader(Context context, ApplicationComponent appComponent) {
        super(context);
        mApplicationComponent = appComponent;
    }

    @Override
    protected BaseViewModel initializeInjector() {
        ViewModelHolder viewModelHolder = new ViewModelHolder();
        DaggerHomeComponent.builder()
                .applicationComponent(mApplicationComponent)
                .build()
                .inject(viewModelHolder);
        return viewModelHolder.mViewModel;
    }

    public class ViewModelHolder {
        @Inject
        HomeFragmentViewModel mViewModel;

        private ViewModelHolder() {

        }
    }
}
