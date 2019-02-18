package com.sniper.deliver.api.common

abstract class Environment {

    abstract val productionUrl: String

    abstract val testUrl: String

    fun getBuildUrl(isDebug: Boolean): String =
            when {
                isDebug -> testUrl
                else -> productionUrl
            }
}
