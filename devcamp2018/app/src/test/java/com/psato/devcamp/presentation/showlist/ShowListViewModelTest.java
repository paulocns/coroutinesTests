package com.psato.devfest.presentation.showlist;

import com.psato.devcamp.data.entity.ShowInfo;
import com.psato.devcamp.interactor.usecase.show.SearchShows;
import com.psato.devcamp.presentation.showlist.ShowListViewModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import rx.Subscriber;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by psato on 30/10/16.
 */
public class ShowListViewModelTest {

    private ShowListViewModel mViewModel;
    @Mock
    private SearchShows mSearchShows;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mViewModel = new ShowListViewModel(mSearchShows);
    }

    @Test
    public void testSearch() {
        mViewModel.setQuery("big");
        assertTrue(mViewModel.getShowList().isEmpty());
        mViewModel.onSearchClicked();
        assertTrue(mViewModel.getShowLoading());
        ArgumentCaptor<Subscriber> captor = ArgumentCaptor.forClass(Subscriber.class);
        Mockito.verify(mSearchShows).setQuery("big");
        Mockito.verify(mSearchShows).execute(captor.capture());
        Subscriber subscriber = captor.getValue();
        ArrayList<ShowInfo> list = new ArrayList<>();
        list.add(Mockito.mock(ShowInfo.class));
        subscriber.onNext(list);
        assertFalse(mViewModel.getShowLoading());
        assertFalse(mViewModel.getShowList().isEmpty());
    }
}