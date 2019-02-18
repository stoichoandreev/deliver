package com.sniper.deliver.adapter

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapterDelegate<T, VH : BaseViewHolder> {

    abstract fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    @CallSuper
    open fun bindViewHolder(holder: VH, model: T, position: Int) {
        //unused
    }
}
