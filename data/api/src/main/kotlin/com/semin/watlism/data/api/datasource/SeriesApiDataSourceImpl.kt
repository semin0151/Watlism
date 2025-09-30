package com.semin.watlism.data.api.datasource

import com.semin.watlism.data.api.TmdbTvApi
import com.semin.watlism.data.datasource.api.SeriesApiDataSource
import com.semin.watlism.data.model.SeriesCreditsData
import com.semin.watlism.data.model.SeriesDetailData
import retrofit2.HttpException
import javax.inject.Inject

class SeriesApiDataSourceImpl @Inject constructor(
    private val tmdbTvApi: TmdbTvApi
) : SeriesApiDataSource {
    override suspend fun getDetails(seriesId: Long): Result<SeriesDetailData> {
        return tmdbTvApi.getDetails(seriesId = seriesId).run {
            if (isSuccessful) {
                val body = this.body()

                if (body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.toSeriesDetailData())
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }

    override suspend fun getCredits(seriesId: Long): Result<SeriesCreditsData> {
        return tmdbTvApi.getCredits(seriesId = seriesId).run {
            if(isSuccessful) {
                val body = this.body()

                if(body == null) {
                    Result.failure(IllegalStateException("Body is null."))
                } else {
                    Result.success(body.toSeriesCreditsData())
                }
            } else {
                Result.failure(HttpException(this))
            }
        }
    }
}