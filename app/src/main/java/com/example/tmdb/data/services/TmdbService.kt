package com.example.tmdb.data.services

import com.example.tmdb.data.discover.entities.responses.MovieDiscoverResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TmdbService {

    @GET("/3/discover/movie")
    suspend fun discover(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("primary_release_year") primary_release_year: Int? = null,
        @Query("primary_release_date.gte") primaryReleaseDateGTE: String? = null,
        @Query("primary_release_date.lte") primaryReleaseDateLTE: String? = null,
        @Query("certification_country") certificationCountry: String? = null, // "US"
        @Query("certification") certification: String? = null, // "R"
    ): Response<MovieDiscoverResponse>
}