package com.devdtoo.moviedaymvvm.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devdtoo.moviedaymvvm.data.api.TheMovieDBInterface
import com.devdtoo.moviedaymvvm.data.vo.MovieDetails
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception

class MovieDetailNetworkDataSource(
    private val apiService: TheMovieDBInterface,
    private val compositeDisposable: CompositeDisposable
) {
//    We'll call api through RxJava. api will return Movie Details, then we assign Movie Details in a LiveData

    private val _networkState = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState // with this get, no need to implement setters and getters to get networkState


    private val _downloadMovieDetailsResponse = MutableLiveData<MovieDetails>()
    val downloadMovieDetailsResponse : LiveData<MovieDetails>
        get() = _downloadMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int) {

        _networkState.postValue(NetworkState.LOADED)
            compositeDisposable.add(
                apiService.getMovieDetails(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadMovieDetailsResponse.postValue(it)
                            _networkState.postValue(NetworkState.LOADED)
                        },{
                            _networkState.postValue(NetworkState.ERROR)
                            Log.e("MovieDetailsDataSource", it.message!!)
                        }
                    )
            )
        try {

        }catch (e: Exception) {
            Log.e("MovieDetailsDataSource", e.message!!)
        }

    }

}