package com.psato.devcamp.presentation.MVVM

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.view.View
import com.psato.devcamp.interactor.usecase.show.SearchShows
import javax.inject.Inject

class QueryViewModelArc @Inject
constructor(private val searchShows: SearchShows?) : ViewModel() {

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
        queryObserver = Observer { query -> searchEnabled.value = !TextUtils.isEmpty(query) }
        query.observeForever(queryObserver)
    }

    override fun onCleared() {
        super.onCleared()
        query.removeObserver(queryObserver)
    }

    fun onQueryClick(view: View) {
        searchShow()
    }

    private fun searchShow() {
        if (searchShows != null) {
            showLoading.value = true
            searchShows.unsubscribe()
            searchShows.query = query.value
            searchShows.execute({ title: String ->
                showLoading.value = false
                result.value = title
            }, { throwable ->
                showLoading.value = false
                result.value = throwable.message
            })
        }
    }

}
