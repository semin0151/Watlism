package com.semin.watlism.data.api

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.api.dto.MovieDetailDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbMovieApi {
    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Path("movie_id") movieId: Long,
        @Query("language") language: String = "ko-KR",
    ): Response<MovieDetailDto>
}