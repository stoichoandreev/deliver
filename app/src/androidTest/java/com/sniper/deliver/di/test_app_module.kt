package com.sniper.deliver.di

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.koin.dsl.module.module

val testApplicationModule = module {

    single(override = true) { ApplicationProvider.getApplicationContext<Context>() }

}
