package com.example.tmdb.data.discover

import com.example.tmdb.data.discover.enums.CertificationCountry
import com.example.tmdb.data.discover.enums.CertificationType
import com.example.tmdb.data.discover.enums.SortBy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import java.util.*
import javax.inject.Inject

class DiscoverRepository @Inject constructor(
    private val remoteDataSource: DiscoverRemoteDataSourceImpl,
    private val localDataSource: DiscoverLocalDataSource
) {

    suspend fun discoverMoviesAsync(
        coroutineScope: CoroutineScope,
        sortBy: SortBy,
        primaryReleaseYear: Int? = null,
        primaryReleaseDateGTE: Date? = null,
        primaryReleaseDateLTE: Date? = null,
        page: Int,
        certificationCountry: CertificationCountry? = null,
        certificationType: CertificationType? = null
    ) = coroutineScope.async(Dispatchers.IO) {
        remoteDataSource.discoverMovies(
            sortBy = sortBy,
            primaryReleaseYear = primaryReleaseYear,
            primaryReleaseDateGTE = primaryReleaseDateGTE,
            primaryReleaseDateLTE = primaryReleaseDateLTE,
            page = page,
            certificationCountry,
            certificationType
        )
    }

}