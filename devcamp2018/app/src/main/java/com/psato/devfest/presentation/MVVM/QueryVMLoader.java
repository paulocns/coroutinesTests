package com.psato.devfest.presentation.MVVM;

import android.content.Context;

import com.psato.devfest.di.component.ApplicationComponent;
import com.psato.devfest.di.component.DaggerQueryComponent;
import com.psato.devfest.presentation.base.BaseViewModel;
import com.psato.devfest.presentation.base.ViewModelLoader;

import javax.inject.Inject;

/**
 * Created by psato on 29/10/16.
 */

public class QueryVMLoader extends ViewModelLoader {
    private ApplicationComponent mApplicationComponent;

    public QueryVMLoader(Context context, ApplicationComponent appComponent) {
        super(context);
        mApplicationComponent = appComponent;
    }

    @Override
    protected BaseViewModel initializeInjector() {
        ViewModelHolder viewModelHolder = new ViewModelHolder();
        DaggerQueryComponent.builder()
                .applicationComponent(mApplicationComponent)
                .build()
                .inject(viewModelHolder);
        return viewModelHolder.mViewModel;
    }

    public class ViewModelHolder {
        @Inject
        QueryViewModel mViewModel;

        private ViewModelHolder() {

        }
    }
}
