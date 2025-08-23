package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.datasource.api.model.TitlesResponseData

interface TrendingApiDataSource {
    suspend fun getAll(): Result<TitlesResponseData>
}