package com.psato.devcamp.interactor.usecase.show

import android.support.annotation.VisibleForTesting
import android.support.annotation.VisibleForTesting.PROTECTED
import com.psato.devcamp.data.entity.Rating
import com.psato.devcamp.data.entity.ShowResponse
import com.psato.devcamp.data.repository.show.ShowRepository
import com.psato.devcamp.interactor.usecase.UseCase
import javax.inject.Inject

/**
 * Created by psato on 29/10/16.
 */

open class SearchShows @Inject
constructor(private val showRepository: ShowRepository) :
        UseCase<List<ShowResponse>>() {

    var query: String? = null

    @VisibleForTesting(otherwise = PROTECTED)
    public override suspend fun executeOnBackground(): List<ShowResponse> {
        query?.let { query ->
            return showRepository.searchShow(query).map {
                background {
                    val rating: Rating = showRepository.showRating(it.show!!.ids!!.trakt!!)
                    ShowResponse(it.show!!.title!!, rating.rating)
                }
            }.map {
                it.await()
            }
        }
        return arrayListOf()
    }
}
