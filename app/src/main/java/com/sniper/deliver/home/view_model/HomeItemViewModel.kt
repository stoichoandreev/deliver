package com.sniper.deliver.home.view_model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class HomeItemViewModel(val id: Int,
                        val title: String,
                        val overview: String,
                        val imageUrl: String) : Parcelable {

    class HomeItemViewModelBuilder {
        var id = -1
        var title = ""
        var overview = ""
        var imageUrl = ""

        fun build(): HomeItemViewModel =
            HomeItemViewModel(id, title, overview, imageUrl)
    }

}

inline fun createHomeItemViewModel(init: HomeItemViewModel.HomeItemViewModelBuilder.() -> Unit): HomeItemViewModel {
    val builder = HomeItemViewModel.HomeItemViewModelBuilder()
    builder.init()
    return builder.build()
}
