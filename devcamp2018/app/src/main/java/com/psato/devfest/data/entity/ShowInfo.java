package com.psato.devfest.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by psato on 29/10/16.
 */

public class ShowInfo {

    @SerializedName("type")
    private String mType;
    @SerializedName("score")
    private String mScore;
    @SerializedName("show")
    private Show mShow;

    public String getType() {
        return mType;
    }

    public String getScore() {
        return mScore;
    }

    public Show getShow() {
        return mShow;
    }
}
