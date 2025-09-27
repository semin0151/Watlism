package com.semin.watlism.data.api

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.api.dto.TitlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbTrendingApi {
    @GET("trending/all/{time_window}")
    suspend fun getAll(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Path("time_window") timeWindow: String,
        @Query("language") language: String
    ): Response<TitlesResponseDto>
}