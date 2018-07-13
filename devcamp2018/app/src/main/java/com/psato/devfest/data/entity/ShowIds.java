package com.psato.devfest.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by psato on 29/10/16.
 */

public class ShowIds {
    @SerializedName("trakt")
    private String mTrakt;
    @SerializedName("slug")
    private String mSlug;
    @SerializedName("tvdb")
    private String mTvdb;
    @SerializedName("imdb")
    private String mImdb;
    @SerializedName("tmdb")
    private String mTmdb;
    @SerializedName("tvrage")
    private String mTvrage;

}
