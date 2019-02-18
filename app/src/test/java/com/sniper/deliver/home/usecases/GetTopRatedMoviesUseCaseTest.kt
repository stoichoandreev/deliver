package com.sniper.deliver.home.usecases

import com.sniper.deliver.home.services.TopRatedMoviesService
import com.sniper.deliver.home.view_model.HomeItemViewModel
import com.sniper.deliver.home.view_model.createHomeItemViewModel
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenMock


@RunWith(MockitoJUnitRunner::class)
class GetTopRatedMoviesUseCaseTest {

    @Mock
    private lateinit var mockTopRatedMoviesApiService: TopRatedMoviesService

    private val notificationsThread = Schedulers.trampoline()

    private val workerThread = Schedulers.trampoline()

    private lateinit var tested: GetTopRatedMoviesUseCase

    @Before
    fun setUp() {
        tested = GetTopRatedMoviesUseCase(mockTopRatedMoviesApiService, notificationsThread, workerThread)
    }

    @Test
    fun `test GetTopRatedMoviesUseCase gets all top rated movies from TopRatedMoviesService`() {
        //given
        whenMock(mockTopRatedMoviesApiService.getMovies()).thenReturn(Observable.just(listOf()))
        //when
        tested.getTopRatedMovies()
        //test
        Mockito.verify(mockTopRatedMoviesApiService).getMovies()
    }

    @Test
    fun `test GetTopRatedMoviesUseCase gets all top rated movies from TopRatedMoviesService and delivers them with the expected view data type`() {
        //given
        val convertedHomeListData = listOf(
            createHomeItemViewModel {
                id = 1
                title = "Movie1"
                overview = "Movie1 overview"
                imageUrl = "image1.jpg"
            },
            createHomeItemViewModel {
                id = 2
                title = "Movie2"
                overview = "Movie2 overview"
                imageUrl = "image2.jpg"
            },
            createHomeItemViewModel {
                id = 3
                title = "Movie3"
                overview = "Movie3 overview"
                imageUrl = "image3.jpg"
            }
        )
        whenMock(mockTopRatedMoviesApiService.getMovies()).thenReturn(Observable.just(convertedHomeListData))
        val testObserver = TestObserver<List<HomeItemViewModel>>()
        //when
        val observable = tested.getTopRatedMovies()
        //test
        observable.subscribe(testObserver)
        testObserver.assertComplete()
        testObserver.assertNoErrors()
        testObserver.assertValueCount(1)
        val listResult = testObserver.values()[0]
        assertEquals(3, listResult.size)

        assertEquals(1, listResult[0].id)
        assertEquals("Movie1", listResult[0].title)
        assertEquals("Movie1 overview", listResult[0].overview)
        assertEquals("image1.jpg", listResult[0].imageUrl)

        assertEquals(2, listResult[1].id)
        assertEquals("Movie2", listResult[1].title)
        assertEquals("Movie2 overview", listResult[1].overview)
        assertEquals("image2.jpg", listResult[1].imageUrl)

        assertEquals(3, listResult[2].id)
        assertEquals("Movie3", listResult[2].title)
        assertEquals("Movie3 overview", listResult[2].overview)
        assertEquals("image3.jpg", listResult[2].imageUrl)
    }

    @Test
    fun `test GetTopRatedMoviesUseCase throws error if TopRatedMoviesService fails`() {
        //given
        whenMock(mockTopRatedMoviesApiService.getMovies()).thenReturn(Observable.error(Throwable("")))
        val testObserver = TestObserver<List<HomeItemViewModel>>()
        //when
        val observable = tested.getTopRatedMovies()
        //test
        observable.subscribe(testObserver)
        testObserver.assertError(Throwable::class.java)
    }
}
