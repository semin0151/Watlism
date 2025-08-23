package com.semin.watlism.data.api.config

import com.semin.watlism.data.api.BuildConfig

object ApiConfig {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val BEARER_TOKEN = "Bearer ${BuildConfig.ACCESS_TOKEN}"
}