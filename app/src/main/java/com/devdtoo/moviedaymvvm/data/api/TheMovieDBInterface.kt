package com.devdtoo.moviedaymvvm.data.api

import com.devdtoo.moviedaymvvm.data.vo.MovieDetails
import com.devdtoo.moviedaymvvm.data.vo.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//        "https://api.themoviedb.org/3/movie/popular?api_key=7ff17a25c7da1faef085e0a8406e9dc7"
//        "https://api.themoviedb.org/3/movie/top_rated?api_key=7ff17a25c7da1faef085e0a8406e9dc7"
//         https://image.tmdb.org/t/p/w185
//         "https://api.themoviedb.org/3/"

interface TheMovieDBInterface {

    @GET("movie/popular")
    fun getPopularMovie(@Query("page")page: Int): Single<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id")id: Int): Single<MovieDetails>


}








