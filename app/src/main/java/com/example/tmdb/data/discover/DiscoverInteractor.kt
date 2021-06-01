package com.example.tmdb.data.discover

import com.example.tmdb.data.base_objects.Resource
import com.example.tmdb.data.discover.entities.bo.Movie
import com.example.tmdb.data.discover.entities.responses.MovieDiscoverResponse
import com.example.tmdb.data.discover.enums.CertificationCountry
import com.example.tmdb.data.discover.enums.CertificationType
import com.example.tmdb.data.discover.enums.MoviesCategory
import com.example.tmdb.data.discover.enums.SortBy
import com.example.tmdb.data.discover.entities.bo.Category
import com.example.tmdb.utils.StringProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.flow
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class DiscoverInteractor @Inject constructor(
    private val discoverRepository: DiscoverRepository,
    private val stringProvider: StringProvider
) {

    suspend fun getMainScreenData(coroutineScope: CoroutineScope, dataList: Array<MoviesCategory>) =
        flow {

            // TODO: 6/1/21 download all categories async and emit every response that was received without waiting for all
            val dataListDeferred = dataList.map { movieCategory ->
                when (movieCategory) {
                    MoviesCategory.Upcoming -> {
                        discoverUpcomingMoviesAsync(coroutineScope)
                    }
                    MoviesCategory.TopRated -> {
                        discoverTopRatedMoviesAsync(coroutineScope)
                    }
                    MoviesCategory.Popular -> {
                        discoverPopularMoviesAsync(coroutineScope)
                    }
                    MoviesCategory.NowPlaying -> {
                        discoverNowPlayingMoviesAsync(coroutineScope)
                    }
                }
            }
            val responsesList = dataListDeferred.awaitAll()
            val categoriesList = getCategoriesList(responsesList, dataList)
            emit(categoriesList)
        }

    private fun getCategoriesList(
        responsesList: List<Resource<MovieDiscoverResponse>>,
        dataList: Array<MoviesCategory>
    ) = responsesList.mapIndexed { index, resource ->
        Category<Movie>(
            stringProvider.getString(dataList[index].stringResource),
            resource.data?.toResult() ?: ArrayList()
        )
    }

    private suspend fun discoverUpcomingMoviesAsync(coroutineScope: CoroutineScope) =
        discoverRepository.discoverMoviesAsync(
            coroutineScope,
            sortBy = SortBy.DESC,
            primaryReleaseDateGTE = getCurrentDate(),
            page = 1
        )

    private suspend fun discoverTopRatedMoviesAsync(coroutineScope: CoroutineScope) =
        discoverRepository.discoverMoviesAsync(
            coroutineScope,
            sortBy = SortBy.VoteAverageDesc,
            certificationCountry = CertificationCountry.US,
            certificationType = CertificationType.R,
            page = 1
        )

    private suspend fun discoverPopularMoviesAsync(coroutineScope: CoroutineScope) =
        discoverRepository.discoverMoviesAsync(
            coroutineScope,
            sortBy = SortBy.PopularityDesc,
            page = 1
        )

    private suspend fun discoverNowPlayingMoviesAsync(coroutineScope: CoroutineScope) =
        discoverRepository.discoverMoviesAsync(
            coroutineScope,
            sortBy = SortBy.DESC,
            primaryReleaseDateGTE = getLastMonthDate(),
            primaryReleaseDateLTE = getCurrentDate(),
            page = 1
        )

    private suspend fun getCurrentDate() = Calendar.getInstance().time

    private suspend fun getLastMonthDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1)
        return calendar.time
    }
}