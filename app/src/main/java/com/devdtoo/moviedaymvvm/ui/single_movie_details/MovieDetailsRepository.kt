package com.devdtoo.moviedaymvvm.ui.single_movie_details

import androidx.lifecycle.LiveData
import com.devdtoo.moviedaymvvm.data.api.TheMovieDBInterface
import com.devdtoo.moviedaymvvm.data.repository.MovieDetailNetworkDataSource
import com.devdtoo.moviedaymvvm.data.repository.NetworkState
import com.devdtoo.moviedaymvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsRepository(private val apiService: TheMovieDBInterface) {

    lateinit var movieDetailsNetworkDataSource: MovieDetailNetworkDataSource

    fun fetchSingleMovieDetails (compositeDisposable: CompositeDisposable, movieId: Int) : LiveData<MovieDetails> {

        movieDetailsNetworkDataSource = MovieDetailNetworkDataSource(apiService, compositeDisposable)
        movieDetailsNetworkDataSource.fetchMovieDetails(movieId)

        return movieDetailsNetworkDataSource.downloadMovieDetailsResponse
    }

    fun getMovieDetailsNetworkState(): LiveData<NetworkState> {
        return movieDetailsNetworkDataSource.networkState
    }

}