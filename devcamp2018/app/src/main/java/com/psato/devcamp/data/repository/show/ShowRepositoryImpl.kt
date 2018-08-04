package com.psato.devcamp.data.repository.show

import com.psato.devcamp.data.entity.ShowInfo
import com.psato.devcamp.data.remote.TraktAPI
import java.io.IOException

/**
 * Created by psato on 29/06/16.
 */

class ShowRepositoryImpl(private val traktAPI: TraktAPI) : ShowRepository {

    @Throws(IOException::class)
    override fun searchShow(query: String): List<ShowInfo>? {
        return traktAPI.searchForShows(query, 100).execute().body()
    }
}
