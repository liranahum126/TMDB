package com.example.tmdb.data.discover

import com.example.tmdb.ApiKey
import javax.inject.Inject

class DiscoverRepository @Inject constructor(
    private val remoteDataSource: DiscoverRemoteDataSourceImpl,
    private val localDataSource: DiscoverLocalDataSource
) {

    suspend fun discoverMoviesLocally() {
        remoteDataSource
    }

    suspend fun discoverMoviesRemotely(sortBy: SortBy) =
        remoteDataSource.discoverMovies(ApiKey.API_KEY, sortBy.typeString, 1)


}