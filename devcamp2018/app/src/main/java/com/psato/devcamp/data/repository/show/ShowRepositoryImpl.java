package com.psato.devcamp.data.repository.show;

import com.psato.devcamp.data.entity.ShowInfo;
import com.psato.devcamp.data.entity.ShowRating;
import com.psato.devcamp.data.remote.TraktAPI;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by psato on 29/06/16.
 */

public class ShowRepositoryImpl implements ShowRepository {
    private TraktAPI traktAPI;

    public ShowRepositoryImpl(TraktAPI traktAPI) {
        this.traktAPI = traktAPI;
    }

    @Override
    public Single<List<ShowInfo>> searchShow(final String query) {
        return traktAPI.searchForShows(query, 200);
    }

    @Override
    public Single<ShowRating> showRating(String id) {
        return traktAPI.getShowRating(id);
    }
}
