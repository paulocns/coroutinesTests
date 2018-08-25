package com.psato.devcamp.presentation.MVVM;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.psato.devcamp.data.entity.ShowResponse;
import com.psato.devcamp.interactor.usecase.show.SearchShows;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

public class QueryViewModelArc extends ViewModel {

    private MutableLiveData<String> result = new MutableLiveData<>();

    private MutableLiveData<String> query = new MutableLiveData<>();

    private MutableLiveData<Boolean> showLoading = new MutableLiveData<>();

    private MutableLiveData<Boolean> searchEnabled = new MutableLiveData<>();

    private Observer<String> queryObserver;

    private SearchShows searchShows;

    @Inject
    public QueryViewModelArc(SearchShows searchShows) {
        this.searchShows = searchShows;
        showLoading.setValue(false);
        searchEnabled.setValue(false);
        result.setValue("");
        query.setValue("");
        queryObserver = query -> searchEnabled.setValue(!TextUtils.isEmpty(query));
        query.observeForever(queryObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        query.removeObserver(queryObserver);
    }

    public void onQueryClick(View view) {
        searchShow(query.getValue());
    }

    private void searchShow(String value) {
        if (searchShows != null) {
            showLoading.setValue(true);
            searchShows.unsubscribe();
            searchShows.setQuery(value);
            Date start = new Date();
            searchShows.execute((Consumer<List<ShowResponse>>) title -> {
                Date end = new Date();
                Log.e("SATO", "SATO - Time: " + (end.getTime() - start.getTime())/1000 + "s");
                showLoading.setValue(false);
                result.setValue(title.get(0).getName());
            }, throwable -> showLoading.setValue(false));
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

}
