package com.example.tmdb.data.local

import com.example.tmdb.data.discover.entities.enums.MoviesCategory
import javax.inject.Inject

class MoviesCategoriesProvider @Inject constructor() {

    fun getMoviesCategories() = arrayOf(
        MoviesCategory.NowPlaying,
        MoviesCategory.Popular,
        MoviesCategory.TopRated,
        MoviesCategory.Upcoming
    )
}