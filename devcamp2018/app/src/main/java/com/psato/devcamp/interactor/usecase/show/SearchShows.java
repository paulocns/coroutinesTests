package com.psato.devcamp.interactor.usecase.show;

import com.psato.devcamp.data.entity.ShowInfo;
import com.psato.devcamp.data.entity.ShowResponse;
import com.psato.devcamp.data.repository.resource.ResourceRepository;
import com.psato.devcamp.data.repository.show.ShowRepository;
import com.psato.devcamp.interactor.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by psato on 29/10/16.
 */

public class SearchShows extends UseCase {
    private ShowRepository showRepository;
    private String query;

    @Inject
    public SearchShows(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    protected Single<List<ShowResponse>> buildUseCaseObservable() {
        return showRepository.searchShow(query).flatMapPublisher(
                (Function<List<ShowInfo>, Flowable<ShowInfo>>) Flowable::fromIterable)
                .flatMapSingle((Function<ShowInfo, SingleSource<ShowResponse>>)
                                showInfo -> showRepository.showRating(showInfo.getShow().getIds().getTrakt())
                                        .map(rating -> new ShowResponse(showInfo.getShow().getTitle(), rating
                                                .getRating())).subscribeOn(Schedulers.io()),

                        false, 4).toList();
    }
}
