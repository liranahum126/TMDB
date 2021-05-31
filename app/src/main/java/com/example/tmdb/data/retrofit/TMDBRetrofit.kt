package com.example.tmdb.data.retrofit

import com.example.tmdb.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TMDBRetrofit(private val gson: Gson) {

    private lateinit var retrofit: Retrofit

    init {
    }

    fun <T> create(service: Class<T>): T {

        retrofit = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(getLoggerInterceptor())
                    .build()
            )
            .build()
        return retrofit.create(service)
    }

    private fun getBaseUrl() = BuildConfig.BASE_URL

    private fun getLoggerInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }
}