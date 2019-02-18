package com.sniper.deliver.api.common

interface OkHttpConfiguration {

    val connectTimeout: Long

    val readTimeout: Long

    val writeTimeout: Long
}
