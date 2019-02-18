package com.sniper.deliver.api.movies_api

import com.sniper.deliver.api.common.ApiConfiguration
import com.sniper.deliver.api.common.RetrofitClient
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit

class MoviesApiClient(converterFactory: Converter.Factory,
                      callAdapterFactory: CallAdapter.Factory,
                      okHttpClient: OkHttpClient,
                      config: ApiConfiguration
) : RetrofitClient {

    private val restAdapter: Retrofit = Retrofit.Builder()
            .baseUrl(config.baseURL)
            .addConverterFactory(converterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(okHttpClient)
            .validateEagerly(true)
            .build()

    override fun <T> api(service: Class<T>): T {
        return restAdapter.create(service)
    }
}
