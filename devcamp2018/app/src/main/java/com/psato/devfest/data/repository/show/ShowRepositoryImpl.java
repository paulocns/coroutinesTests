package com.psato.devfest.data.repository.show;

import com.psato.devfest.data.entity.ShowInfo;
import com.psato.devfest.data.remote.TraktAPI;

import java.util.List;

import rx.Observable;

/**
 * Created by psato on 29/06/16.
 */

public class ShowRepositoryImpl implements ShowRepository {
    private TraktAPI mTraktAPI;

    public ShowRepositoryImpl(TraktAPI traktAPI) {
        mTraktAPI = traktAPI;
    }

    @Override
    public Observable<List<ShowInfo>> searchShow(final String query) {
        return mTraktAPI.searchForShows(query, 100);
    }
}
