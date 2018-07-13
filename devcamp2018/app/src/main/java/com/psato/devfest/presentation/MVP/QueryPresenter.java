package com.psato.devfest.presentation.MVP;

import android.support.annotation.NonNull;
import android.util.Log;

import com.psato.devfest.interactor.rx.DefaultSubscriber;
import com.psato.devfest.interactor.usecase.show.SearchShows;
import com.psato.devfest.presentation.base.BasePresenter;

import javax.inject.Inject;

public class QueryPresenter extends BasePresenter implements QueryContract.Presenter {
    private SearchShows mSearchShows;
    private QueryContract.View mView;

    @Inject
    public QueryPresenter (@NonNull SearchShows searchShows) {
        mSearchShows = searchShows;
    }

    @Override
    public void start() {
        //nothing to do
    }

    @Override
    public void stop() {
        if(mSearchShows != null){
            mSearchShows.unsubscribe();
        }
        mSearchShows = null;
    }

    @Override
    public void setView(QueryContract.View view) {
        mView = view;
    }

    @Override
    public void requestData(String value) {
        searchShow(value);
    }

    private void searchShow(String value){
        if(mSearchShows != null && mView != null) {
            mView.showLoading();
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
            if(mView != null){
                mView.hideLoading();
                mView.showError();
            }
            Log.e("SATO","onError",e);
        }

        @Override
        public void onNext(String title) {
            if(mView != null) {
                mView.hideLoading();
                mView.showResult(title);
            }
        }
    }
}
