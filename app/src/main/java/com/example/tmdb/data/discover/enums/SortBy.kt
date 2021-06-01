package com.example.tmdb.data.discover.enums

enum class SortBy(val typeString: String) {
    DESC("desc"), ASC("asc"), PopularityDesc("popularity.desc"), VoteAverageDesc("vote_average.desc")
}