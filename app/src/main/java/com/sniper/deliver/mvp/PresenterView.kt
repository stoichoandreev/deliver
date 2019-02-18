package com.sniper.deliver.mvp

interface PresenterView {

    fun showError(errorMessage: String)

    fun showLoading(show: Boolean)

}
