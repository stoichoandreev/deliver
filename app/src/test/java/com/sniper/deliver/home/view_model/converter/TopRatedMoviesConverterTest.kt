package com.sniper.deliver.home.view_model.converter

import com.sniper.deliver.api.movies_api.models.Movie
import org.junit.Test

import org.junit.Assert.*

class TopRatedMoviesConverterTest {

    private val tested = TopRatedMoviesConverter()

    @Test
    fun `test TopRatedMoviesConverter converts top rated movies api response to home item view model list`() {
        //given
        val movie1 = Movie()
        val movie2 = Movie()
        with(movie1) {
            id = 1
            voteCount = 12
            video = true
            voteAverage = 4.5
            title = "Name"
            popularity = 6.8
            posterPath = "poster1.jpg"
            overview = "Some overview of movie 1"
            releaseDate = "22/03/2018"
        }
        with(movie2) {
            id = 2
            voteCount = 22
            video = false
            voteAverage = 3.4
            title = "SecondName"
            popularity = 5.6
            posterPath = "poster2.jpg"
            overview = "Some overview of movie 2"
            releaseDate = "02/06/2017"
        }
        val moviesList = listOf(movie1, movie2)
        //when
        val result = tested.map(moviesList)
        //test
        assertEquals(2, result.size)

        assertEquals(1, result[0].id)
        assertEquals("Name", result[0].title)
        assertEquals(TopRatedMoviesConverter.IMAGE_BASE_URL + "poster1.jpg", result[0].imageUrl)
        assertEquals("Some overview of movie 1", result[0].overview)

        assertEquals(2, result[1].id)
        assertEquals("SecondName", result[1].title)
        assertEquals(TopRatedMoviesConverter.IMAGE_BASE_URL + "poster2.jpg", result[1].imageUrl)
        assertEquals("Some overview of movie 2", result[1].overview)
    }

    @Test
    fun `test TopRatedMoviesConverter converts empty top rated movies api response to empty home item view model list`() {
        //given
        val moviesList = listOf<Movie>()
        //when
        val result = tested.map(moviesList)
        //test
        assertEquals(0, result.size)
    }
}
