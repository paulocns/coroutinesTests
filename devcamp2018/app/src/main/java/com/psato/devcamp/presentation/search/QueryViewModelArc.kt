package com.psato.devcamp.presentation.search

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.psato.devcamp.data.entity.ShowResponse
import com.psato.devcamp.interactor.usecase.show.SearchShows
import java.util.*
import javax.inject.Inject

class QueryViewModelArc @Inject
constructor(private val searchShows: SearchShows) : ViewModel() {

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
        searchShows.unsubscribe()
        query.removeObserver(queryObserver)
    }

    fun onQueryClick(view: View) {
        searchShow()
    }

    private fun searchShow() {
        showLoading.value = true
        searchShows.unsubscribe()
        searchShows.query = query.value
        val start = Date()
        searchShows.execute({ list: List<ShowResponse> ->
            val end = Date()
            Log.e("SATO", "SATO - Time: " + (end.time - start.time) / 1000 + "s")
            showLoading.value = false
            result.value = list[0].name
        }, { throwable ->
            showLoading.value = false
            result.value = throwable.message
        })
    }

}
