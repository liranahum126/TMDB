package com.example.tmdb.data.discover.entities.bo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// TODO: 6/1/21 create another ui Movie object to hold the minimum required fields to init the main fragment
@Parcelize
data class Movie(
    val mAdult: Boolean?,
    val mBackdropPath: String?,
    val mGenreIds: List<Int>?,
    val mId: Int?,
    val mOriginalLanguage: String?,
    val mOriginalTitle: String?,
    val mOverview: String?,
    val mPopularity: Double?,
    val mPosterPath: String?,
    val mReleaseDate: String?,
    val mTitle: String?,
    val mVideo: Boolean?,
    val mVoteAverage: Float?,
    val mVoteCount: Int?
) : Parcelable