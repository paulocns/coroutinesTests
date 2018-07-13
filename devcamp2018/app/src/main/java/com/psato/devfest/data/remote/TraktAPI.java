package com.psato.devfest.data.remote;

import com.psato.devfest.data.entity.ShowInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by psato on 29/10/16.
 */

public interface TraktAPI {
    @Headers({APIConstants.HEADER_API_VERSION + ": 2",
            APIConstants.HEADER_CLIENT_ID + ": " + APIConstants.CLIENT_ID})
    @GET("search/show")
    Observable<List<ShowInfo>> searchForShows(@Query("query") String query, @Query("limit") int limit);
}
