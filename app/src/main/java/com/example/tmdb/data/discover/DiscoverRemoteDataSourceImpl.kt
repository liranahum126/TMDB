package com.example.tmdb.data.discover

import com.example.tmdb.ApiKey
import com.example.tmdb.data.BaseDataSource
import com.example.tmdb.data.Resource
import com.example.tmdb.data.discover.entities.responses.MovieDiscoverResponse
import com.example.tmdb.data.discover.enums.CertificationCountry
import com.example.tmdb.data.discover.enums.CertificationType
import com.example.tmdb.data.discover.enums.SortBy
import com.example.tmdb.data.services.TmdbService
import com.example.tmdb.utils.toServerDateFormat
import java.util.*
import javax.inject.Inject

class DiscoverRemoteDataSourceImpl @Inject constructor(private val tmdbService: TmdbService) :
    BaseDataSource()/*, DiscoverRemoteDataSource*/ {

    suspend fun discoverMovies(
        sortBy: SortBy,
        primaryReleaseYear: Int? = null,
        primaryReleaseDateGTE: Date? = null,
        primaryReleaseDateLTE: Date? = null,
        page: Int,
        certificationCountry: CertificationCountry? = null,
        certificationType: CertificationType? = null
    ): Resource<MovieDiscoverResponse> {

        return getResult {
            tmdbService.discover(
                apiKey = ApiKey.API_KEY_STRING,
                sortBy = sortBy.typeString,
                primary_release_year = primaryReleaseYear,
                primaryReleaseDateGTE = primaryReleaseDateGTE?.toServerDateFormat(),
                primaryReleaseDateLTE = primaryReleaseDateLTE?.toServerDateFormat(),
                page = page,
                certificationCountry = certificationCountry?.name,
                certification = certificationType?.name,
            )
        }
    }


}