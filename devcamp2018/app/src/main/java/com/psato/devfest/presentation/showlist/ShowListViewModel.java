package com.psato.devfest.presentation.showlist;

import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.psato.devfest.BR;
import com.psato.devfest.data.entity.ShowInfo;
import com.psato.devfest.interactor.rx.DefaultSubscriber;
import com.psato.devfest.interactor.usecase.show.SearchShows;
import com.psato.devfest.presentation.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by psato on 29/10/16.
 */

public class ShowListViewModel extends BaseViewModel {

    private SearchShows mSearchShows;

    private String mQuery;

    private boolean mShowLoading;

    private List<ShowInfo> mShowInfoList = new ArrayList<>();

    @Inject
    public ShowListViewModel(@NonNull  SearchShows searchShows) {
        mSearchShows = searchShows;
    }

    @Bindable
    public void setQuery(String query){
        mQuery = query;
    }

    @Bindable
    public String getQuery(){
        return mQuery;
    }

    @Bindable
    public List<ShowInfo> getShowList(){
        return mShowInfoList;
    }

    public void onSearchClicked(){
        searchShow();
    }

    public boolean onEditorAction(@NonNull final TextView textView, final int actionId,
                                  @Nullable final KeyEvent keyEvent) {
        if(actionId == EditorInfo.IME_ACTION_DONE){
            searchShow();
            return true;
        }
        return false;
    }

    @Override
    public void start() {
        //nothing to do
    }

    @Override
    public void stop() {
        if(mSearchShows != null) {
            mSearchShows.unsubscribe();
        }
    }
    @Bindable
    public boolean getShowLoading(){
        return mShowLoading;
    }

    public void setShowLoading(boolean isLoading){
        if(isLoading != mShowLoading){
            mShowLoading = isLoading;
            notifyPropertyChanged(BR.showLoading);
        }
    }

    private void searchShow(){
        if(mSearchShows != null) {
            setShowLoading(true);
            mSearchShows.unsubscribe();
            mSearchShows.setQuery(mQuery);
            mSearchShows.execute(new SearchSubscriber());
        }
    }



    private class SearchSubscriber extends DefaultSubscriber<List<ShowInfo>> {

        SearchSubscriber() {
        }

        @Override
        public void onError(Throwable e) {
            setShowLoading(false);
            Log.e("SATO","onError",e);
        }

        @Override
        public void onNext(List<ShowInfo> showInfos) {
            setShowLoading(false);
            mShowInfoList.clear();
            mShowInfoList.addAll(showInfos);
            notifyPropertyChanged(BR.showList);
        }
    }

}
