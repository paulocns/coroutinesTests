package com.psato.devcamp.interactor.usecase.show

import com.psato.devcamp.data.entity.Rating
import com.psato.devcamp.data.entity.Show
import com.psato.devcamp.data.entity.ShowIds
import com.psato.devcamp.data.entity.ShowInfo
import com.psato.devcamp.data.repository.show.ShowRepository
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.experimental.BlockingChecker
import kotlinx.coroutines.experimental.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Matchers.*
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.robolectric.RobolectricTestRunner
import kotlin.coroutines.experimental.Continuation

@RunWith(RobolectricTestRunner::class)
class SearchShowsTest {

    lateinit var usecase: SearchShows

    lateinit var showRepositoryMock: ShowRepository

    @Before
    fun setUp() {
        showRepositoryMock = PowerMockito.mock(ShowRepository::class.java)
        usecase = Mockito.spy(SearchShows(showRepositoryMock))
    }

    @After
    fun tearDown() {
//        Mockito.verifyNoMoreInteractions(usecase, showRepositoryMock)
    }

    class mockChecker : BlockingChecker {
        override fun checkRunBlocking() =
                check(true) { "runBlocking is not allowed in Android main looper thread" }
    }

    @Test
    fun executeOnBackground_query() {
        runBlocking {
            //Arrange
            val query = "game"
            usecase.query = query
            val resultList = arrayListOf<ShowInfo>()
            val showInfo = ShowInfo()
            val ids = ShowIds()
            val id = "id"
            val show = Show()
            val title = "title"
            val ratingValue = 10.0
            show.title = title
            ids.trakt = id
            show.ids = ids
            showInfo.show = show
            val rating = Rating()
            rating.rating = ratingValue
            resultList.add(showInfo)
            PowerMockito.doReturn(resultList).`when`(showRepositoryMock, "searchShow", anyString(), any(Continuation::class.java))
            PowerMockito.doReturn(rating).`when`(showRepositoryMock, "showRating", anyString(), any(Continuation::class.java))
            //Act
            val result = usecase.executeOnBackground()
            //Assert
            Mockito.verify(usecase).executeOnBackground()
            PowerMockito.verifyPrivate(showRepositoryMock).invoke("searchShow", eq(query), any(Continuation::class.java))
            PowerMockito.verifyPrivate(showRepositoryMock).invoke("showRating", eq(id), any(Continuation::class.java))
            assertEquals(1, result.size)
            assertEquals(title, result[0].name)
            assertEquals(ratingValue, result[0].rating)
        }
    }
}