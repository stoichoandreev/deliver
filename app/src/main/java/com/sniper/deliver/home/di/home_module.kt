package com.sniper.deliver.home.di

import com.sniper.deliver.api.common.RetrofitClient
import com.sniper.deliver.api.movies_api.apis.TopRatedApi
import com.sniper.deliver.di.KoinDependency
import com.sniper.deliver.di.Scope
import com.sniper.deliver.home.presenter.DefaultHomePresenter
import com.sniper.deliver.home.presenter.HomePresenter
import com.sniper.deliver.home.services.DefaultTopRatedMoviesService
import com.sniper.deliver.home.services.TopRatedMoviesService
import com.sniper.deliver.home.usecases.GetTopRatedMoviesUseCase
import com.sniper.deliver.home.view_model.converter.TopRatedMoviesConverter
import org.koin.dsl.module.module

val homeModule = module {

    scope(Scope.HOME, override = true) {
        val client = get(KoinDependency.MOVIES_API_RETROFIT_CLIENT) as RetrofitClient
        client.api(TopRatedApi::class.java)
    }

    scope(Scope.HOME, override = true) {
        TopRatedMoviesConverter()
    }

    scope(Scope.HOME, override = true) {
        DefaultTopRatedMoviesService(get(), get()) as TopRatedMoviesService
    }

    scope(Scope.HOME, override = true) {
        GetTopRatedMoviesUseCase(get(),get(KoinDependency.MAIN_THREAD), get(KoinDependency.BACKGROUND_THREAD))
    }

    scope(Scope.HOME, override = true) {
        DefaultHomePresenter(get(), get()) as HomePresenter
    }

}
