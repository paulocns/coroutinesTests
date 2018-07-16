package com.psato.devcamp.presentation.home;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.psato.devcamp.di.component.ApplicationComponent;
import com.psato.devcamp.presentation.base.BaseViewModel;
import com.psato.devcamp.presentation.base.ViewModelLoader;

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
    protected ViewModel initializeInjector() {
//        ViewModelHolder viewModelHolder = new ViewModelHolder();
//        DaggerHomeComponent.builder()
//                .applicationComponent(mApplicationComponent)
//                .build()
//                .inject(viewModelHolder);
        return null;
    }

    public class ViewModelHolder {
        @Inject
        HomeFragmentViewModel mViewModel;

        private ViewModelHolder() {

        }
    }
}
