package com.example.tmdb.data.discover

import com.example.tmdb.data.discover.entities.enums.MoviesCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DiscoverInteractor @Inject constructor(
    private val discoverRepository: DiscoverRepository
) {


    suspend fun getMainScreenData(coroutineScope: CoroutineScope, dataList: Array<MoviesCategory>) =
        flow {

            // TODO: 6/1/21 download all categories async and emit every response that was received without waiting for all
            val dataListDeferred = dataList.map { movieCategory ->
                when (movieCategory) {
                    MoviesCategory.Upcoming -> {
                        discoverUpcomingMoviesAsync(coroutineScope)

//                        What movies are in theatres?
//                        URL: /discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22
                    }
                    MoviesCategory.TopRated -> {
//                        What are the highest rated movies rated R?
//                        URL: /discover/movie/?certification_country=US&certification=R&sort_by=vote_average.desc
                    }
                    MoviesCategory.Popular -> {
                        discoverPopularMoviesAsync(coroutineScope)
//                        What are the most popular movies?
//                        URL: /discover/movie?sort_by=popularity.desc
                    }
                    MoviesCategory.NowPlaying -> {
                        discoverNowPlayingMoviesAsync(coroutineScope)
//                        What movies are in theatres?
//                        URL: /discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22
                    }
                }
            }
            emit(dataListDeferred.awaitAll())
        }

    suspend fun discoverPopularMoviesAsync(coroutineScope: CoroutineScope) =
        coroutineScope.async(Dispatchers.IO) {
            val response = discoverRepository.discoverMoviesRemotely(SortBy.PopularityDesc)
            response.data?.toResult() ?: ArrayList()
        }

    suspend fun discoverUpcomingMoviesAsync(coroutineScope: CoroutineScope) =
        coroutineScope.async(Dispatchers.IO) {
            val response = discoverRepository.discoverMoviesRemotely(SortBy.DESC)
            response.data?.toResult() ?: ArrayList()
        }

    suspend fun discoverNowPlayingMoviesAsync(coroutineScope: CoroutineScope) =
        coroutineScope.async(Dispatchers.IO) {
            val response = discoverRepository.discoverMoviesRemotely(SortBy.DESC)
            response.data?.toResult() ?: ArrayList()
        }
}