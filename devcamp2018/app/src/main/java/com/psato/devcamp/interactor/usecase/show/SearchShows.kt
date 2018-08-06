package com.psato.devcamp.interactor.usecase.show

import com.psato.devcamp.data.repository.resource.ResourceRepository
import com.psato.devcamp.data.repository.show.ShowRepository
import com.psato.devcamp.interactor.usecase.UseCase
import javax.inject.Inject

/**
 * Created by psato on 29/10/16.
 */

class SearchShows @Inject
constructor(private val showRepository: ShowRepository, private val resourceRepository: ResourceRepository) :
        UseCase<String>() {

    var query: String? = null

    override suspend fun executeOnBackground(): String {
        query?.let {
            val showsInfo = showRepository.searchShow(it)
            val showName: String? = showsInfo?.getOrNull(0)?.show?.title
            return showName ?: resourceRepository.notFoundShow
        }
        return ""
    }
}
