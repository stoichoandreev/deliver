package com.sniper.deliver.home.services

import com.sniper.deliver.api.movies_api.apis.TopRatedApi
import com.sniper.deliver.api.movies_api.models.ResponseTopRatedMovies
import com.sniper.deliver.home.view_model.converter.TopRatedMoviesConverter
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenMock


@RunWith(MockitoJUnitRunner::class)
class DefaultTopRatedMoviesServiceTest {

    @Mock
    private lateinit var mockTopRatedApi: TopRatedApi

    @Mock
    private lateinit var mockTopRatedMoviesConverter: TopRatedMoviesConverter

    private lateinit var tested: DefaultTopRatedMoviesService

    @Before
    fun setUp() {
        tested = DefaultTopRatedMoviesService(mockTopRatedApi, mockTopRatedMoviesConverter)
    }

    @Test
    fun `test DefaultTopRatedMoviesService fetches all top rated movies from TopRatedApi and converts the response`() {
        //given
        val topRatedServerResponse = ResponseTopRatedMovies()
        whenMock(mockTopRatedApi.topRatedMovies()).thenReturn(Observable.just(topRatedServerResponse))
        //when
        tested.getMovies().test()
        //test
        Mockito.verify(mockTopRatedApi).topRatedMovies()
        Mockito.verify(mockTopRatedMoviesConverter).map(topRatedServerResponse.results)
        Mockito.verifyNoMoreInteractions(mockTopRatedApi)
    }
}