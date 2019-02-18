package com.sniper.deliver.home.view_model.converter

import com.sniper.deliver.api.movies_api.models.Movie
import com.sniper.deliver.converter.BaseConverter
import com.sniper.deliver.home.view_model.HomeItemViewModel
import com.sniper.deliver.home.view_model.createHomeItemViewModel

class TopRatedMoviesConverter : BaseConverter<List<Movie>, List<HomeItemViewModel>> {

    companion object {
        const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w400"
    }

    override fun map(response: List<Movie>): List<HomeItemViewModel> =
            response.map {
                createHomeItemViewModel {
                    id = it.id
                    title = it.title
                    overview = it.overview
                    imageUrl = IMAGE_BASE_URL + it.posterPath
                }
            }
}
