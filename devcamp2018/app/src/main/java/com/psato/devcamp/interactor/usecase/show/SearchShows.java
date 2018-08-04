package com.psato.devcamp.interactor.usecase.show;

import com.psato.devcamp.data.repository.resource.ResourceRepository;
import com.psato.devcamp.data.repository.show.ShowRepository;
import com.psato.devcamp.interactor.usecase.UseCase;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by psato on 29/10/16.
 */

public class SearchShows extends UseCase {
    private ShowRepository mShowRepository;
    private ResourceRepository mResourceRepository;
    private String mQuery;

    @Inject
    public SearchShows(ShowRepository showRepository, ResourceRepository resourceRepository) {
        mShowRepository = showRepository;
        mResourceRepository = resourceRepository;
    }

    public void setQuery(String query) {
        mQuery = query;
    }

    @Override
    protected Single<String> buildUseCaseObservable() {
//        return mShowRepository.searchShow(mQuery).map(showInfos -> {
//            if (showInfos != null && !showInfos.isEmpty()
//                    && showInfos.get(0).getShow() != null) {
//                return showInfos.get(0).getShow().getTitle();
//            } else {
//                return mResourceRepository.getNotFoundShow();
//            }
//        });
        return Single.just("1");
    }
}
