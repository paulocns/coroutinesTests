package com.psato.devcamp.data.repository.show;

import com.psato.devcamp.data.entity.ShowInfo;
import com.psato.devcamp.data.entity.ShowRating;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by psato on 29/06/16.
 */

public interface ShowRepository {
    Single<List<ShowInfo>> searchShow(String query);

    Single<ShowRating> showRating(String id);
}
