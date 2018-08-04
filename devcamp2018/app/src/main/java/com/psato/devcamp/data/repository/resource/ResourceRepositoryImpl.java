package com.psato.devcamp.data.repository.resource;

import com.psato.devcamp.infrastructure.DevCampApplication;
import com.psato.devcamp.R;

/**
 * Created by psato on 29/06/16.
 */

public class ResourceRepositoryImpl implements ResourceRepository {
    DevCampApplication devCampApplication;

    public ResourceRepositoryImpl(DevCampApplication application) {
        devCampApplication = application;
    }


    @Override
    public String getNotFoundShow() {
        return devCampApplication.getResources().getString(R.string.error);
    }
}
