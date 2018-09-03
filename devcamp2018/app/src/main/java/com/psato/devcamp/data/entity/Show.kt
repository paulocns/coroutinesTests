package com.psato.devcamp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by psato on 29/10/16.
 */

class Show {

    @SerializedName("title")
    var title: String? = null
    @SerializedName("year")
    var year: Int? = null
    @SerializedName("ids")
    var ids: ShowIds? = null
}
