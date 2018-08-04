package com.psato.devcamp.presentation.MVVM

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.view.View

import com.psato.devcamp.interactor.usecase.show.SearchShows

import javax.inject.Inject

import io.reactivex.functions.Consumer

class QueryViewModelArc @Inject
constructor(private val mSearchShows: SearchShows?) : ViewModel() {

    val result = MutableLiveData<String>()

    val query = MutableLiveData<String>()

    val showLoading = MutableLiveData<Boolean>()

    val searchEnabled = MutableLiveData<Boolean>()

    private val queryObserver: Observer<String>

    init {
        showLoading.value = false
        searchEnabled.value = false
        result.value = ""
        query.value = ""
        queryObserver =  Observer { query -> searchEnabled.setValue(!TextUtils.isEmpty(query))}
        query.observeForever(queryObserver)
    }

    override fun onCleared() {
        super.onCleared()
        query.removeObserver(queryObserver)
    }

    fun onQueryClick(view: View) {
        searchShow(query.value)
    }

    private fun searchShow(value: String?) {
        if (mSearchShows != null) {
//            showLoading.setValue(true)
//            mSearchShows.unsubscribe()
//            mSearchShows.setQuery(value)
//            mSearchShows.execute({ title ->
//                showLoading.setValue(false)
//                result.setValue(title)
//            } as Consumer<String>, { throwable -> showLoading.setValue(false) })
        }
    }

}
