package com.psato.devcamp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by psato on 29/10/16.
 */

class ShowIds {
    @SerializedName("trakt")
    var trakt: String? = null
    @SerializedName("slug")
    var slug: String? = null
    @SerializedName("tvdb")
    var tvdb: String? = null
    @SerializedName("imdb")
    var imdb: String? = null
    @SerializedName("tmdb")
    var tmdb: String? = null
    @SerializedName("tvrage")
    var tvrage: String? = null

}
