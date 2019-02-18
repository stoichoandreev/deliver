package com.sniper.deliver.di

import com.sniper.deliver.api.common.RetrofitClient
import com.sniper.deliver.api.movies_api.MoviesApiClient
import org.koin.dsl.module.module

val testNetworkModule = module {

    single(KoinDependency.MOVIES_API_RETROFIT_CLIENT, override = true) {
        //Just replace with your mock retrofit client
        MoviesApiClient(get(KoinDependency.GSON_CONVERTER_FACTORY),
                get(KoinDependency.RX_CALL_ADAPTER_FACTORY),
                get(KoinDependency.MOVIES_API_OK_HTTP_CLIENT),
                get(KoinDependency.MOVIES_API_CONFIGURATION)) as RetrofitClient
    }

}
