package com.example.tmdb.data.discover.enums

import androidx.annotation.StringRes
import com.example.tmdb.R

enum class MoviesCategory(@StringRes val stringResource: Int) {
    NowPlaying(R.string.movies_now_playing), Popular(R.string.popular_movies), TopRated(R.string.movies_top_rated), Upcoming(
        R.string.movies_upcoming
    )
}