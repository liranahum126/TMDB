package com.example.tmdb.data.discover

import com.example.tmdb.data.BaseDataSource
import com.example.tmdb.data.Resource
import com.example.tmdb.data.discover.entities.responses.MovieDiscoverResponse
import com.example.tmdb.data.retrofit.TMDBRetrofit
import com.example.tmdb.data.services.TmdbService
import com.google.gson.GsonBuilder
import java.util.*
import javax.inject.Inject

class DiscoverRemoteDataSourceImpl @Inject constructor(/*private val tmdbService: TmdbService*/) :
    BaseDataSource()/*, DiscoverRemoteDataSource*/ {

    suspend fun discoverMovies(
        apiKey: String,
        sortBy: String,
        primaryReleaseYear: Int? = null,
        primaryReleaseDateGTE: Date? = null,
        primaryReleaseDateLTE: Date? = null,
        page: Int,
    ): Resource<MovieDiscoverResponse> {
        val retrofitService = TMDBRetrofit(GsonBuilder().create())
        val tmdbService = retrofitService.create(TmdbService::class.java)
        return getResult {
            tmdbService.discover(
                apiKey = apiKey,
                sortBy = sortBy,
                page = page,
                primary_release_year = primaryReleaseYear,
                primaryReleaseDateGTE = primaryReleaseDateGTE,
                primaryReleaseDateLTE = primaryReleaseDateLTE
            )
        }
    }


}