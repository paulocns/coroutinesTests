package com.psato.devcamp.data.repository.resource

import com.psato.devcamp.infrastructure.DevCampApplication
import com.psato.devcamp.R

/**
 * Created by psato on 29/06/16.
 */

class ResourceRepositoryImpl(var mDevCampApplication: DevCampApplication) : ResourceRepository {


    override val notFoundShow: String
        get() = mDevCampApplication.resources.getString(R.string.error)
}
