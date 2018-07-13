package com.psato.devfest.presentation.showlist;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.psato.devfest.data.entity.ShowInfo;

/**
 * Created by psato on 30/10/16.
 */

public class ShowItemViewModel extends BaseObservable {

    private ShowInfo mShowInfo;

    public ShowItemViewModel(ShowInfo showInfo) {
        mShowInfo = showInfo;
    }

    @Bindable
    public String getShowName(){
        return mShowInfo.getShow().getTitle();
    }

    @Bindable
    public String getScore(){
        return mShowInfo.getScore();
    }
}
