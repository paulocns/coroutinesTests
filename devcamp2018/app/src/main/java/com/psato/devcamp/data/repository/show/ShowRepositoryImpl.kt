package com.psato.devcamp.data.repository.show

import com.psato.devcamp.data.entity.Rating
import com.psato.devcamp.data.entity.ShowInfo
import com.psato.devcamp.data.remote.TraktAPI
import ru.gildor.coroutines.retrofit.await
import java.io.IOException

/**
 * Created by psato on 29/06/16.
 */

class ShowRepositoryImpl(private val traktAPI: TraktAPI) : ShowRepository {
    override suspend fun showRating(id: String): Rating {
        return traktAPI.getShowRating(id).await()
    }

    @Throws(IOException::class)
    override suspend fun searchShow(query: String): List<ShowInfo> {
        return traktAPI.searchForShows(query, 200).await()
    }
}
