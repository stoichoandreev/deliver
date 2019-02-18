package com.sniper.deliver.api.movies_api.apis

import com.sniper.deliver.api.movies_api.MoviesApiHelper
import com.sniper.deliver.api.movies_api.models.ResponseTopRatedMovies
import io.reactivex.Observable
import retrofit2.http.GET

interface TopRatedApi {

    @GET(MoviesApiHelper.EndPoints.TOP_RATED_MOVIES)
    fun topRatedMovies(): Observable<ResponseTopRatedMovies>

}
