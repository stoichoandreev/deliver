package com.sniper.deliver.di.modules

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.sniper.deliver.BuildConfig
import com.sniper.deliver.api.common.ApiConfiguration
import com.sniper.deliver.api.common.OkHttpConfiguration
import com.sniper.deliver.api.common.RetrofitClient
import com.sniper.deliver.api.movies_api.MoviesApiClient
import com.sniper.deliver.api.movies_api.MoviesApiClientInterceptor
import com.sniper.deliver.api.movies_api.MoviesApiConfiguration
import com.sniper.deliver.api.movies_api.MoviesApiEnvironment
import com.sniper.deliver.api.movies_api.MoviesApiOkHttpConfiguration
import com.sniper.deliver.di.KoinDependency
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.DateFormat
import java.util.concurrent.TimeUnit

val networkModule = module {

    single(KoinDependency.MOVIES_API_OK_HTTP_CONFIG) {
        MoviesApiOkHttpConfiguration() as OkHttpConfiguration
    }

    single(KoinDependency.HTTP_BODY_LOGGING) {
        val logLevel = when {
            (BuildConfig.DEBUG) -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        HttpLoggingInterceptor().setLevel(logLevel) as HttpLoggingInterceptor
    }

    single(KoinDependency.MOVIES_API_CLIENT_INTERCEPTOR) {
        MoviesApiClientInterceptor() as Interceptor
    }

    single(KoinDependency.DEFAULT_GSON_SETUP) {
        GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .setPrettyPrinting()
                .create()
    }

    single(KoinDependency.GSON_CONVERTER_FACTORY) {
        GsonConverterFactory.create(get(KoinDependency.DEFAULT_GSON_SETUP)) as Converter.Factory
    }

    single(KoinDependency.RX_CALL_ADAPTER_FACTORY) {
        RxJava2CallAdapterFactory.create() as CallAdapter.Factory
    }

    single(KoinDependency.MOVIES_API_CONFIGURATION) {
        MoviesApiConfiguration(MoviesApiEnvironment, BuildConfig.DEBUG) as ApiConfiguration
    }

    single(KoinDependency.MOVIES_API_OK_HTTP_CLIENT) {
        val config = get<OkHttpConfiguration>(KoinDependency.MOVIES_API_OK_HTTP_CONFIG)
        val apiInterceptor = get<Interceptor>(KoinDependency.MOVIES_API_CLIENT_INTERCEPTOR)
        val bodyLogging = get<HttpLoggingInterceptor>(KoinDependency.HTTP_BODY_LOGGING)
        val builder = OkHttpClient.Builder()
                .connectTimeout(config.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(config.readTimeout, TimeUnit.SECONDS)
                .writeTimeout(config.writeTimeout, TimeUnit.SECONDS)
        builder.interceptors().add(apiInterceptor)
        builder.interceptors().add(bodyLogging)
        builder.build()
    }

    single(KoinDependency.MOVIES_API_RETROFIT_CLIENT) {
        MoviesApiClient(get(KoinDependency.GSON_CONVERTER_FACTORY),
                get(KoinDependency.RX_CALL_ADAPTER_FACTORY),
                get(KoinDependency.MOVIES_API_OK_HTTP_CLIENT),
                get(KoinDependency.MOVIES_API_CONFIGURATION)) as RetrofitClient
    }

    single(KoinDependency.BACKGROUND_THREAD) {
        Schedulers.io()
    }

    single(KoinDependency.MAIN_THREAD) {
        AndroidSchedulers.mainThread() as Scheduler
    }

}
