package com.sniper.deliver.api.movies_api

import okhttp3.Interceptor
import okhttp3.Response

class MoviesApiClientInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url().newBuilder()
            .addQueryParameter(MoviesApiParams.API_KEY, MoviesApiHelper.API_KEY)
            .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

}
