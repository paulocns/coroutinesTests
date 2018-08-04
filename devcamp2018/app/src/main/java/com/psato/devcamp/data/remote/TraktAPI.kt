package com.psato.devcamp.data.remote

import com.psato.devcamp.data.entity.ShowInfo

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by psato on 29/10/16.
 */

interface TraktAPI {
    @Headers(APIConstants.HEADER_API_VERSION + ": 2",
            APIConstants.HEADER_CLIENT_ID + ": " + APIConstants.CLIENT_ID)
    @GET("search/show")
    fun searchForShows(@Query("query") query: String, @Query("limit") limit: Int): Call<List<ShowInfo>>
}
