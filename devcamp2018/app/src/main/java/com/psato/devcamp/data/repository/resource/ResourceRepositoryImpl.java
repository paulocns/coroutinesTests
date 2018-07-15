package com.psato.devcamp.data.repository.resource;

import com.psato.devcamp.infrastructure.DevCampApplication;
import com.psato.devcamp.R;
import com.psato.devcamp.infrastructure.DevCampApplication;

/**
 * Created by psato on 29/06/16.
 */

public class ResourceRepositoryImpl implements ResourceRepository {
    DevCampApplication mDevCampApplication;

    public ResourceRepositoryImpl(DevCampApplication application) {
        mDevCampApplication = application;
    }


    @Override
    public String getNotFoundShow() {
        return mDevCampApplication.getResources().getString(R.string.error);
    }
}
