package com.sniper.deliver.home.cache

import androidx.lifecycle.ViewModel
import com.sniper.deliver.home.presenter.HomePresenter


class HomeCache: ViewModel() {

    var presenter: HomePresenter? = null

    fun hasCache (): Boolean {
        return presenter != null
    }
    override fun onCleared() {
        super.onCleared()
        presenter?.destroy()
        presenter = null
    }
}
