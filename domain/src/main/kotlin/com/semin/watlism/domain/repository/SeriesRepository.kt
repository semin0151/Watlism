package com.semin.watlism.domain.repository

import com.semin.watlism.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    fun getPopularSeries(): Flow<List<Series>>

    fun getSeries(
        country: String? = null,
        firstAirDateGte: String? = null,
        voteCountGte: Double? = null,
        withGenres: Long? = null,
        withoutGenres: List<Long>? = null,
    ) : Flow<List<Series>>
}