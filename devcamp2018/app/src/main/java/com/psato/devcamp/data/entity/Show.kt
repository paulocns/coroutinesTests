package com.psato.devcamp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by psato on 29/10/16.
 */

class Show {

    @SerializedName("title")
    val title: String? = null
    @SerializedName("year")
    val year: Int? = null
    @SerializedName("ids")
    val ids: ShowIds? = null
}
