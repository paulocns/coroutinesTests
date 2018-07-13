package com.psato.devfest.presentation.MVP;

import android.content.Context;

import com.psato.devfest.di.component.ApplicationComponent;
import com.psato.devfest.di.component.DaggerQueryMVPComponent;
import com.psato.devfest.presentation.base.BasePresenter;
import com.psato.devfest.presentation.base.PresenterLoader;

import javax.inject.Inject;

public class QueryPresenterLoader extends PresenterLoader {
    private ApplicationComponent mApplicationComponent;

    public QueryPresenterLoader(Context context, ApplicationComponent appComponent) {
        super(context);
        mApplicationComponent = appComponent;
    }

    @Override
    protected BasePresenter initializeInjector() {
        PresenterHolder PresenterHolder = new PresenterHolder();
        DaggerQueryMVPComponent.builder()
                .applicationComponent(mApplicationComponent)
                .build()
                .inject(PresenterHolder);
        return PresenterHolder.mPresenter;
    }

    public class PresenterHolder {
        @Inject
        QueryPresenter mPresenter;

        private PresenterHolder() {

        }
    }
}
