package com.psato.devcamp.data.repository.resource

import com.psato.devcamp.infrastructure.DevCampApplication
import com.psato.devcamp.R

/**
 * Created by psato on 29/06/16.
 */

class ResourceRepositoryImpl(var devCampApplication: DevCampApplication) : ResourceRepository {


    override val notFoundShow: String
        get() = devCampApplication.resources.getString(R.string.error)
}
