package com.sniper.deliver.home.usecases

import com.sniper.deliver.home.services.TopRatedMoviesService
import com.sniper.deliver.home.view_model.HomeItemViewModel
import io.reactivex.Observable
import io.reactivex.Scheduler

class GetTopRatedMoviesUseCase(private val moviesService: TopRatedMoviesService,
                               private val notifications: Scheduler,
                               private val worker: Scheduler) {

    fun getTopRatedMovies(): Observable<List<HomeItemViewModel>> =
            moviesService.getMovies()
                    .subscribeOn(worker)
                    .observeOn(notifications)
}
