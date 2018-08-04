package com.psato.devcamp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by psato on 29/10/16.
 */

class ShowInfo {

    @SerializedName("type")
    val type: String? = null
    @SerializedName("score")
    val score: String? = null
    @SerializedName("show")
    val show: Show? = null
}
