package com.semin.watlism.data.api.datasource

import com.semin.watlism.data.api.api.TmdbTrendingApi
import com.semin.watlism.data.datasource.api.TrendingApiDataSource
import com.semin.watlism.data.datasource.api.model.TitlesResponseData
import retrofit2.HttpException
import javax.inject.Inject

class TrendingApiDataSourceImpl @Inject constructor(
    private val tmdbTrendingApi: TmdbTrendingApi
) : TrendingApiDataSource {
    override suspend fun getAll(): Result<TitlesResponseData> {
        return tmdbTrendingApi.getAll(
            timeWindow = "day",
            language = "ko-KR"
        ).run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.toTitlesResponseData())
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }
}