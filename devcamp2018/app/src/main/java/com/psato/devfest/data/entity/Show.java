package com.psato.devfest.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by psato on 29/10/16.
 */

public class Show {

    @SerializedName("title")
    private String mTitle;
    @SerializedName("year")
    private Integer mYear;
    @SerializedName("ids")
    private ShowIds mIds;


    public String getTitle() {
        return mTitle;
    }

    public Integer getYear() {
        return mYear;
    }

    public ShowIds getIds() {
        return mIds;
    }
}
