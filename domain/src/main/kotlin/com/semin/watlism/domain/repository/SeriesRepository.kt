package com.semin.watlism.domain.repository

import com.semin.watlism.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    fun getPopularSeries(): Flow<List<Series>>
}