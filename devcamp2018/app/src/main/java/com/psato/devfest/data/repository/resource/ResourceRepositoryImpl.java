package com.psato.devfest.data.repository.resource;

import com.psato.devfest.R;
import com.psato.devfest.infrastructure.DevFestApplication;

/**
 * Created by psato on 29/06/16.
 */

public class ResourceRepositoryImpl implements ResourceRepository {
    DevFestApplication mDevFestApplication;

    public ResourceRepositoryImpl(DevFestApplication application) {
        mDevFestApplication = application;
    }


    @Override
    public String getNotFoundShow() {
        return mDevFestApplication.getResources().getString(R.string.error);
    }
}
