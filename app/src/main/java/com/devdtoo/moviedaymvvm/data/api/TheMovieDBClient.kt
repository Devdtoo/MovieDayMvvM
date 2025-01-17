package com.devdtoo.moviedaymvvm.data.api

import android.util.TimeUtils
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

//        "https://api.themoviedb.org/3/movie/popular?api_key=7ff17a25c7da1faef085e0a8406e9dc7"
//        "https://api.themoviedb.org/3/movie/top_rated?api_key=7ff17a25c7da1faef085e0a8406e9dc7"
//         https://image.tmdb.org/t/p/w185
//         "https://api.themoviedb.org/3/"
const val API_KEY = "7ff17a25c7da1faef085e0a8406e9dc7"
const val BASE_URL = "https://api.themoviedb.org/3/"
const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w185"

const val FIRST_PAGE = 1
const val POST_PER_PAGE = 20


object TheMovieDBClient {

//    Using RxJava, Retrofit n OkHttp

    fun getClient(): TheMovieDBInterface{

        val requestInterceptor = Interceptor { chain ->

            val url = chain.request()
                .url()
                .newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()

            return@Interceptor chain.proceed(request)

        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMovieDBInterface::class.java)

    }

}