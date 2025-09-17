package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.datasource.api.model.MovieResponseData
import com.semin.watlism.data.datasource.api.model.SeriesResponseData

interface DiscoverApiDataSource {
    suspend fun getPopularMovies(primaryReleaseDate: String): Result<MovieResponseData>

    suspend fun getPopularSeries(): Result<SeriesResponseData>
}