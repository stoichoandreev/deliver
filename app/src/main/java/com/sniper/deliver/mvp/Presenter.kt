package com.sniper.deliver.mvp

interface Presenter {

    fun attachView(view: PresenterView?)

    fun destroy()

}
