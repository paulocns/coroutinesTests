package com.psato.devcamp.data.repository.show;

import com.psato.devcamp.data.entity.ShowInfo;

import java.util.List;

import rx.Observable;

/**
 * Created by psato on 29/06/16.
 */

public interface ShowRepository {
    Observable<List<ShowInfo>> searchShow(String query);
}
