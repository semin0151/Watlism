package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.model.CreditsData
import com.semin.watlism.data.model.SeriesDetailData

interface SeriesApiDataSource {
    suspend fun getDetails(seriesId: Long): Result<SeriesDetailData>

    suspend fun getCredits(seriesId: Long): Result<List<CreditsData>>
}