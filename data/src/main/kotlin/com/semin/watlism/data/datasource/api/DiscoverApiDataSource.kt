package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.model.MovieResponseData
import com.semin.watlism.data.model.SeriesResponseData

interface DiscoverApiDataSource {
    suspend fun getPopularMovies(primaryReleaseDate: String): Result<MovieResponseData>

    suspend fun getPopularSeries(): Result<SeriesResponseData>

    suspend fun getSeries(
        country: String? = null,
        firstAirDateGte: String? = null,
        voteCountGte: Double? = null,
        withGenres: Long? = null,
        withoutGenres: List<Long>? = null,
    ) : Result<SeriesResponseData>
}