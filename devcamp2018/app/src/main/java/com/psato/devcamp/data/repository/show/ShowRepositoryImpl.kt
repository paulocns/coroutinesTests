package com.psato.devcamp.data.repository.show

import com.psato.devcamp.data.entity.ShowInfo
import com.psato.devcamp.data.remote.TraktAPI

import java.io.IOException

import io.reactivex.Single

/**
 * Created by psato on 29/06/16.
 */

class ShowRepositoryImpl(private val mTraktAPI: TraktAPI) : ShowRepository {

    @Throws(IOException::class)
    override fun searchShow(query: String): List<ShowInfo>? {
        return mTraktAPI.searchForShows(query, 100).execute().body()
    }
}
