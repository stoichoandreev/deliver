package com.sniper.deliver.api.movies_api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class ResponseTopRatedMovies: Serializable {

    @SerializedName("page")
    @Expose
    var page: Int = 0

    @SerializedName("total_results")
    @Expose
    var totalResults: Int = 0

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int = 0

    @SerializedName("results")
    @Expose
    var results: List<Movie> = listOf()

}
