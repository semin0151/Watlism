package com.semin.watlism.data.api

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.api.dto.SeriesCreditsDto
import com.semin.watlism.data.api.dto.SeriesDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbTvApi {
    @GET("tv/{series_id}")
    suspend fun getDetails(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Path("series_id") seriesId: Long,
        @Query("language") language: String = "ko-KR",
    ): Response<SeriesDetailDto>

    @GET("tv/{series_id}/credits")
    suspend fun getCredits(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Path("series_id") seriesId: Long,
        @Query("language") language: String = "ko-KR",
    ): Response<SeriesCreditsDto>
}