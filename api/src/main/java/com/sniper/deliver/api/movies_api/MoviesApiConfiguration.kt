package com.sniper.deliver.api.movies_api

import com.sniper.deliver.api.common.ApiConfiguration
import com.sniper.deliver.api.common.Environment

class MoviesApiConfiguration(private val environment: Environment,
                             private val isDebug: Boolean): ApiConfiguration {

    override val baseURL: String
        get() = environment.getBuildUrl(isDebug)

}
