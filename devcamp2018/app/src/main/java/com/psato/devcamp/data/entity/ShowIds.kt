package com.psato.devcamp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by psato on 29/10/16.
 */

class ShowIds {
    @SerializedName("trakt")
    private val mTrakt: String? = null
    @SerializedName("slug")
    private val mSlug: String? = null
    @SerializedName("tvdb")
    private val mTvdb: String? = null
    @SerializedName("imdb")
    private val mImdb: String? = null
    @SerializedName("tmdb")
    private val mTmdb: String? = null
    @SerializedName("tvrage")
    private val mTvrage: String? = null

}
