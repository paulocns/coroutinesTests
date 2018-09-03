package com.psato.devcamp.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by psato on 29/10/16.
 */

class ShowInfo {

    @SerializedName("type")
    var type: String? = null
    @SerializedName("score")
    var score: String? = null
    @SerializedName("show")
    var show: Show? = null
}
