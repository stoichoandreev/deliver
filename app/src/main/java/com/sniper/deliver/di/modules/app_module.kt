package com.sniper.deliver.di.modules

import io.reactivex.disposables.CompositeDisposable
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val applicationModule = module {

    single { androidContext() }

    factory { CompositeDisposable() }

}
