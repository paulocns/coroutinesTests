package com.psato.devcamp.presentation.MVVM;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.psato.devcamp.interactor.rx.DefaultSubscriber;
import com.psato.devcamp.interactor.usecase.show.SearchShows;

import javax.inject.Inject;

public class QueryViewModelArc extends ViewModel {

    private MutableLiveData<String> result = new MutableLiveData<>();

    private MutableLiveData<String> query = new MutableLiveData<>();

    private MutableLiveData<Boolean> showLoading = new MutableLiveData<>();

    private MutableLiveData<Boolean> searchEnabled =  new MutableLiveData<>();

    private Observer<String> queryObserver;

    private SearchShows mSearchShows;

    @Inject
    public QueryViewModelArc(SearchShows searchShows) {
        mSearchShows = searchShows;
        showLoading.setValue(false);
        searchEnabled.setValue(false);
        result.setValue("");
        query.setValue("");
        queryObserver = query -> searchEnabled.setValue(!TextUtils.isEmpty(query));
        query.observeForever(queryObserver);
    }

    public void onQueryClick(View view) {
        searchShow(query.getValue());
    }

    private void searchShow(String value) {
        if (mSearchShows != null) {
            showLoading.setValue(true);
            mSearchShows.unsubscribe();
            mSearchShows.setQuery(value);
            mSearchShows.execute(new SearchSubscriber());
        }
    }

    public MutableLiveData<Boolean> getSearchEnabled() {
        return searchEnabled;
    }

    public MutableLiveData<Boolean> getShowLoading() {
        return showLoading;
    }

    public MutableLiveData<String> getQuery() {
        return query;
    }

    public MutableLiveData<String> getResult() {
        return result;
    }


    private class SearchSubscriber extends DefaultSubscriber<String> {

        SearchSubscriber() {
        }

        @Override
        public void onError(Throwable e) {
            showLoading.setValue(false);
        }

        @Override
        public void onNext(String title) {
            showLoading.setValue(false);
            result.setValue(title);
        }
    }

}
