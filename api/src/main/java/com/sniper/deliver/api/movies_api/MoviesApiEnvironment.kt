package com.sniper.deliver.api.movies_api

import com.sniper.deliver.api.common.Environment


object MoviesApiEnvironment: Environment() {

    override val productionUrl: String
        get() = MoviesApiHelper.API_PRODUCTION_URL

    override val testUrl: String
        get() = MoviesApiHelper.API_QA_URL

}
