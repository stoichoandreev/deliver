package com.sniper.deliver.api.movies_api

import com.sniper.deliver.api.common.OkHttpConfiguration

class MoviesApiOkHttpConfiguration : OkHttpConfiguration {

    override val connectTimeout: Long
        get() = CONNECTION_TIMEOUT

    override val readTimeout: Long
        get() = READ_TIMEOUT

    override val writeTimeout: Long
        get() = WRITE_TIMEOUT

    companion object {
        const val CONNECTION_TIMEOUT: Long = 30
        const val READ_TIMEOUT: Long = 30
        const val WRITE_TIMEOUT: Long = 30
    }
}
