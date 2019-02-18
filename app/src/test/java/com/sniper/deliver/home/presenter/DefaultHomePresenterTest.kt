package com.sniper.deliver.home.presenter

import com.sniper.deliver.home.usecases.GetTopRatedMoviesUseCase
import com.sniper.deliver.home.view_model.createHomeItemViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenMock


@RunWith(MockitoJUnitRunner::class)
class DefaultHomePresenterTest {
    @Mock
    private lateinit var mockView: HomePresenter.View

    @Mock
    private lateinit var mockGetTopRatedMoviesUseCase: GetTopRatedMoviesUseCase

    @Mock
    private lateinit var mockCompositeDisposable: CompositeDisposable

    private lateinit var tested: DefaultHomePresenter

    @Before
    fun setUp() {
        tested = DefaultHomePresenter(mockGetTopRatedMoviesUseCase, mockCompositeDisposable)
    }

    @Test
    fun `test DefaultHomePresenter fetches top rated movies list with success, but does not call the view if it is not attached`() {
        //given
        val moviesListData = listOf(createHomeItemViewModel { })
        whenMock(mockGetTopRatedMoviesUseCase.getTopRatedMovies()).thenReturn(Observable.just(moviesListData))
        //when
        tested.onFetchTopRatedMovies()
        //test
        Mockito.verifyNoMoreInteractions(mockView)
    }

    @Test
    fun `test DefaultHomePresenter fetches top rated movies list with success and displays them`() {
        //given
        val moviesListData = listOf(createHomeItemViewModel { })
        whenMock(mockGetTopRatedMoviesUseCase.getTopRatedMovies()).thenReturn(Observable.just(moviesListData))
        //when
        tested.attachView(mockView)
        tested.onFetchTopRatedMovies()
        //test
        Mockito.verify(mockView).showLoading(true)
        Mockito.verify(mockView).showTopRatesMovies(moviesListData)
        Mockito.verify(mockView).showLoading(false)
    }

    @Test
    fun `test DefaultHomePresenter fetches top rated movies list with error and displays error message`() {
        //given
        val error = "Some error message"
        whenMock(mockGetTopRatedMoviesUseCase.getTopRatedMovies()).thenReturn(Observable.error(Throwable(error)))
        //when
        tested.attachView(mockView)
        tested.onFetchTopRatedMovies()
        //test
        Mockito.verify(mockView).showLoading(true)
        Mockito.verify(mockView).showError(error)
        Mockito.verify(mockView).showLoading(false)
    }

    @Test
    fun `test DefaultHomePresenter fetches top rated movies from the cache`() {
        //given
        val moviesListData = listOf(createHomeItemViewModel { })
        whenMock(mockGetTopRatedMoviesUseCase.getTopRatedMovies()).thenReturn(Observable.just(moviesListData))
        //when
        tested.attachView(mockView)
        //first time fetch from the server
        tested.onFetchTopRatedMovies()
        //second time fetch from the cache
        tested.onFetchTopRatedMovies()
        //test
        Mockito.verify(mockView).showLoading(true)
        Mockito.verify(mockView, times(2)).showTopRatesMovies(moviesListData)
        Mockito.verify(mockView).showLoading(false)
    }

    @Test
    fun `test DefaultHomePresenter destroys the subscriptions`() {
        //when
        tested.destroy()
        //test
        Mockito.verify(mockCompositeDisposable).dispose()
    }
}