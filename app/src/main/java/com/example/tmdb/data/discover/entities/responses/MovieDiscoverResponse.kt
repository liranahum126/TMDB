package com.example.tmdb.data.discover.entities.responses

import android.os.Parcelable
import com.example.tmdb.data.Mappable
import com.example.tmdb.data.discover.entities.bo.Movie
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDiscoverResponse(
    @SerializedName("page")
    val page: Int?,
    @SerializedName("results")
    val results: List<Result>?,
    @SerializedName("total_pages")
    val totalPages: Int?,
    @SerializedName("total_results")
    val totalResults: Int?
) : Parcelable, Mappable<List<Movie>> {
    @Parcelize
    data class Result(
        @SerializedName("adult")
        val adult: Boolean?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("original_title")
        val originalTitle: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("video")
        val video: Boolean?,
        @SerializedName("vote_average")
        val voteAverage: Int?,
        @SerializedName("vote_count")
        val voteCount: Int?
    ) : Parcelable

    override fun toResult(): List<Movie> {
        return results?.map {
            Movie(
                mAdult = it.adult,
                mBackdropPath = it.backdropPath,
                mGenreIds = it.genreIds,
                mId = it.id,
                mOriginalLanguage = it.originalLanguage,
                mOriginalTitle = it.originalTitle,
                mOverview = it.overview,
                mPopularity = it.popularity,
                mPosterPath = it.posterPath,
                mReleaseDate = it.releaseDate,
                mTitle = it.title,
                mVideo = it.video,
                mVoteAverage = it.voteAverage,
                mVoteCount = it.voteCount
            )
        } ?: ArrayList()
    }
}


















