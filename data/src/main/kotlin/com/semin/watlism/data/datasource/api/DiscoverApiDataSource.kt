package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.datasource.api.model.MovieResponseData

interface DiscoverApiDataSource {
    suspend fun getPopularMovies(primaryReleaseDate: String): Result<MovieResponseData>
}