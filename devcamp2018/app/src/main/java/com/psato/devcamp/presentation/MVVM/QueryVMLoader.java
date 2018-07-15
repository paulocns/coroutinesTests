package com.psato.devcamp.presentation.MVVM;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.psato.devcamp.di.component.ApplicationComponent;
import com.psato.devcamp.di.component.DaggerQueryComponent;
import com.psato.devcamp.presentation.base.BaseViewModel;
import com.psato.devcamp.presentation.base.ViewModelLoader;

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
    protected ViewModel initializeInjector() {
        ViewModelHolder viewModelHolder = new ViewModelHolder();
        DaggerQueryComponent.builder()
                .applicationComponent(mApplicationComponent)
                .build()
                .inject(viewModelHolder);
        return viewModelHolder.mViewModel;
    }

    public class ViewModelHolder {
        @Inject
        QueryViewModelArc mViewModel;

        private ViewModelHolder() {

        }
    }
}
