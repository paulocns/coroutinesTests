package com.psato.devfest.presentation.base;

import android.databinding.BaseObservable;

/**
 * Created by psato on 29/10/16.
 */

public abstract class BaseViewModel extends BaseObservable{
    public abstract void start();
    public abstract void stop();
}
