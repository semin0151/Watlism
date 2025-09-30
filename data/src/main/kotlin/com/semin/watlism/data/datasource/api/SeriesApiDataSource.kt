package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.model.SeriesCreditsData
import com.semin.watlism.data.model.SeriesDetailData

interface SeriesApiDataSource {
    suspend fun getDetails(seriesId: Long): Result<SeriesDetailData>

    suspend fun getCredits(seriesId: Long): Result<SeriesCreditsData>
}