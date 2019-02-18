package com.sniper.deliver.api.common

interface RetrofitClient {

    fun <T> api(service: Class<T>): T

}
