package com.psato.devcamp.interactor.usecase.show

import com.psato.devcamp.data.entity.ShowInfo
import com.psato.devcamp.data.entity.ShowResponse
import com.psato.devcamp.data.repository.show.ShowRepository
import com.psato.devcamp.interactor.usecase.UseCase
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by psato on 29/10/16.
 */

class SearchShows @Inject
constructor(private val showRepository: ShowRepository) : UseCase() {
    var query: String? = null

    override fun buildUseCaseObservable(): Single<List<ShowResponse>> {
        return showRepository.searchShow(query).flatMapPublisher { Flowable.fromIterable(it) }
                .flatMapSingle({ showInfo: ShowInfo ->
                    showRepository.showRating(showInfo.show.ids.trakt)
                            .subscribeOn(Schedulers.io())
                            .map { rating ->
                                ShowResponse(showInfo.show.title, rating
                                        .rating)
                            }
                }, false, 4).toList()
    }
}
