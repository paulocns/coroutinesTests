package com.psato.devcamp.data.repository.show;

import com.psato.devcamp.data.entity.ShowInfo;
import com.psato.devcamp.data.remote.TraktAPI;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by psato on 29/06/16.
 */

public class ShowRepositoryImpl implements ShowRepository {
    private TraktAPI mTraktAPI;

    public ShowRepositoryImpl(TraktAPI traktAPI) {
        mTraktAPI = traktAPI;
    }

    @Override
    public Single<List<ShowInfo>> searchShow(final String query) {
        return mTraktAPI.searchForShows(query, 100);
    }
}
