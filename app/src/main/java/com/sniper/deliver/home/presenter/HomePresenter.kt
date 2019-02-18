package com.sniper.deliver.home.presenter

import com.sniper.deliver.home.view_model.HomeItemViewModel
import com.sniper.deliver.mvp.Presenter
import com.sniper.deliver.mvp.PresenterView

interface HomePresenter : Presenter {

    fun onFetchTopRatedMovies()

    fun onRefreshTopRatedMovies()

    interface View: PresenterView {
        fun showTopRatesMovies(movies: List<HomeItemViewModel>)
    }
}
