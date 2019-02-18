package com.sniper.deliver.home.services

import com.sniper.deliver.home.view_model.HomeItemViewModel
import io.reactivex.Observable

interface TopRatedMoviesService {

    fun getMovies(): Observable<List<HomeItemViewModel>>

}

