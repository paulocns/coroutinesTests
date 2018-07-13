package com.psato.devfest.interactor.usecase.show;

import com.psato.devfest.data.entity.ShowInfo;
import com.psato.devfest.data.repository.resource.ResourceRepository;
import com.psato.devfest.data.repository.show.ShowRepository;
import com.psato.devfest.interactor.usecase.UseCase;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

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
    protected Observable<String> buildUseCaseObservable() {
        return mShowRepository.searchShow(mQuery).map(new Func1<List<ShowInfo>, String>() {
            @Override
            public String call(List<ShowInfo> showInfos) {
                if(showInfos != null && !showInfos.isEmpty()
                        && showInfos.get(0).getShow() != null) {
                    return showInfos.get(0).getShow().getTitle();
                }else{
                    return mResourceRepository.getNotFoundShow();
                }
            }
        });
    }
}
