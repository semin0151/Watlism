package com.semin.watlism.data.api.config

import com.semin.watlism.data.api.BuildConfig

object ApiConfig {
    const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
    const val TMDB_IMAGE_URL = "https://image.tmdb.org/t/p/"
    const val TMDB_ORIGINAL_IMAGE_URL = "${TMDB_IMAGE_URL}original/"
    const val BEARER_TOKEN = "Bearer ${BuildConfig.ACCESS_TOKEN}"
}