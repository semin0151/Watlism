package com.semin.watlism.domain.repository

import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.model.SeriesDetail
import com.semin.watlism.domain.value.TitleId
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

    fun getDetail(titleId: TitleId): Flow<SeriesDetail>
}