package com.sniper.deliver

import android.app.Application
import com.sniper.deliver.di.modules.networkModule
import com.sniper.deliver.di.modules.applicationModule
import org.koin.android.ext.android.startKoin

open class DeliverApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initApplicationModules()
    }

    open fun initApplicationModules() {
        startKoin(this, listOf(applicationModule, networkModule))
    }
}
