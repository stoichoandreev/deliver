package com.sniper.deliver.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sniper.deliver.R
import com.sniper.deliver.adapter.BaseAdapterDelegate
import com.sniper.deliver.adapter.BaseViewHolder
import com.sniper.deliver.home.view_model.HomeItemViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_view.view.homeItemImageView
import kotlinx.android.synthetic.main.item_home_view.view.homeItemTitleView
import kotlinx.android.synthetic.main.item_home_view.view.homeItemOverviewView

class HomeItemDelegate : BaseAdapterDelegate<HomeItemViewModel, HomeItemDelegate.HomeItemViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HomeItemViewHolder(inflater.inflate(R.layout.item_home_view, parent, false))
    }

    override fun bindViewHolder(
        holder: HomeItemViewHolder,
        model: HomeItemViewModel,
        position: Int
    ) {

        holder.titleView.text = model.title
        holder.overview.text = model.overview

        Picasso.with(holder.imageView.context)
            .load(model.imageUrl)
            .placeholder(R.drawable.ic_launcher_background)
            .error(android.R.drawable.stat_notify_error)
            .into(holder.imageView)

        super.bindViewHolder(holder, model, position)
    }

    class HomeItemViewHolder(parent: View) : BaseViewHolder(parent) {
        val imageView: ImageView = parent.homeItemImageView
        val titleView: TextView = parent.homeItemTitleView
        val overview: TextView = parent.homeItemOverviewView
    }

}
