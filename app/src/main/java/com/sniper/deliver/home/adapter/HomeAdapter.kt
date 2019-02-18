package com.sniper.deliver.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sniper.deliver.adapter.BaseViewHolder
import com.sniper.deliver.home.view_model.HomeItemViewModel

class HomeAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private val movieItemDelegate = HomeItemDelegate()

    private val differ: AsyncListDiffer<HomeItemViewModel> =
        AsyncListDiffer(this, object : DiffUtil.ItemCallback<HomeItemViewModel>() {
            override fun areItemsTheSame(old: HomeItemViewModel, new: HomeItemViewModel): Boolean =
                old == new

            override fun areContentsTheSame(old: HomeItemViewModel, new: HomeItemViewModel): Boolean =
                old.id == new.id

        })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val holder: RecyclerView.ViewHolder = movieItemDelegate.createViewHolder(parent)
        return holder as BaseViewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val viewModel = differ.currentList[position]
        movieItemDelegate.bindViewHolder(holder as HomeItemDelegate.HomeItemViewHolder, viewModel, position)
    }

    override fun getItemCount() = differ.currentList.size

    fun setData(data: List<HomeItemViewModel>) = differ.submitList(data)

}
