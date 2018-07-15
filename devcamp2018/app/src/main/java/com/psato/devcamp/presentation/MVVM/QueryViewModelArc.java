package com.psato.devcamp.presentation.MVVM;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.view.View;

import com.psato.devcamp.interactor.rx.DefaultSubscriber;
import com.psato.devcamp.interactor.usecase.show.SearchShows;

import javax.inject.Inject;

public class QueryViewModelArc extends ViewModel {

    public MutableLiveData<String> result = new MutableLiveData<>();

    public MutableLiveData<String> query = new MutableLiveData<>();

    public MutableLiveData<Boolean> showLoading = new MutableLiveData<>();

    private SearchShows mSearchShows;

    @Inject
    public QueryViewModelArc(SearchShows searchShows) {
        mSearchShows = searchShows;
        showLoading.setValue(false);
        result.setValue("");
        query.setValue("");
    }

    public void onQueryClick(View view) {
        searchShow(query.getValue());
    }

    private void searchShow(String value) {
        if (mSearchShows != null) {
            showLoading.postValue(true);
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
            showLoading.postValue(false);
        }

        @Override
        public void onNext(String title) {
            showLoading.postValue(false);
            result.postValue(title);
        }
    }

}
