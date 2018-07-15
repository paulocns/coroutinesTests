package com.psato.devcamp.presentation.showlist;

import android.content.Context;

import com.psato.devcamp.di.component.ApplicationComponent;
import com.psato.devcamp.di.component.DaggerSearchShowComponent;
import com.psato.devcamp.presentation.MVVM.QueryViewModelArc;
import com.psato.devcamp.presentation.base.BaseViewModel;
import com.psato.devcamp.presentation.base.ViewModelLoader;

import javax.inject.Inject;

/**
 * Created by psato on 29/10/16.
 */

public class ShowListVMLoader extends ViewModelLoader {
    private ApplicationComponent mApplicationComponent;

    public ShowListVMLoader(Context context, ApplicationComponent appComponent) {
        super(context);
        mApplicationComponent = appComponent;
    }

    @Override
    protected QueryViewModelArc initializeInjector() {
        ViewModelHolder viewModelHolder = new ViewModelHolder();
        DaggerSearchShowComponent.builder()
                .applicationComponent(mApplicationComponent)
                .build()
                .inject(viewModelHolder);
        return null;//viewModelHolder.mViewModel;
    }

    public class ViewModelHolder {
        @Inject
        ShowListViewModel mViewModel;

        private ViewModelHolder() {

        }
    }
}
