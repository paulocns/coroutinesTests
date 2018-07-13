package com.psato.devfest.presentation.MVVM;

import android.databinding.Bindable;
import android.util.Log;
import android.view.View;

import com.psato.devfest.BR;
import com.psato.devfest.interactor.rx.DefaultSubscriber;
import com.psato.devfest.interactor.usecase.show.SearchShows;
import com.psato.devfest.presentation.base.BaseViewModel;

import javax.inject.Inject;

public class QueryViewModel extends BaseViewModel {

    private String mResult = "";

    private String mQuery = "";

    private boolean mShowLoading = false;
    private SearchShows mSearchShows;


    @Inject
    public QueryViewModel(SearchShows searchShows) {
        mSearchShows = searchShows;
    }

    @Override

    public void start() {

    }

    @Override
    public void stop() {

    }

    public void onQueryClick(View view){
        searchShow(getQuery());
    }

    @Bindable
    public String getQuery(){
        return mQuery;
    }

    @Bindable
    public void setQuery(String query){
        if(query != null && !query.equals(mQuery)){
            mQuery = query;
            notifyPropertyChanged(BR.query);
        }
    }

    @Bindable
    public String getResult(){
        return mResult;
    }

    public void setResult(String result) {
        mResult = result;
        notifyPropertyChanged(BR.result);
    }

    @Bindable
    public boolean getShowLoading(){
        return mShowLoading;
    }

    public void setShowLoading(boolean showLoading) {
        mShowLoading = showLoading;
        notifyPropertyChanged(BR.showLoading);
    }

    private void searchShow(String value){
        if(mSearchShows != null) {
            setShowLoading(true);
            mSearchShows.unsubscribe();
            mSearchShows.setQuery(value);
            mSearchShows.execute(new SearchSubscriber());
        }
    }



    private class SearchSubscriber extends DefaultSubscriber<String> {

        SearchSubscriber() {
        }

        @Override
        public void onError(Throwable e) {
            setShowLoading(false);
            Log.e("SATO","onError",e);
        }

        @Override
        public void onNext(String title) {
                setShowLoading(false);
                setResult(title);
        }
    }
}
