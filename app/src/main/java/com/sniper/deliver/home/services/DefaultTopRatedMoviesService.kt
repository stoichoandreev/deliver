package com.sniper.deliver.home.services

import com.sniper.deliver.api.movies_api.apis.TopRatedApi
import com.sniper.deliver.home.view_model.HomeItemViewModel
import com.sniper.deliver.home.view_model.converter.TopRatedMoviesConverter
import io.reactivex.Observable


class DefaultTopRatedMoviesService(
    private val api: TopRatedApi,
    private val converter: TopRatedMoviesConverter
) : TopRatedMoviesService {

    override fun getMovies(): Observable<List<HomeItemViewModel>> =
        api.topRatedMovies().map { response -> converter.map(response.results) }
}
