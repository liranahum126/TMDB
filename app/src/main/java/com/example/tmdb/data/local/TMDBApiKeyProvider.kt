package com.example.tmdb.data.local

import com.example.tmdb.BuildConfig

class TMDBApiKeyProvider {
    companion object {
        const val API_KEY_STRING = BuildConfig.API_KEY
    }
}