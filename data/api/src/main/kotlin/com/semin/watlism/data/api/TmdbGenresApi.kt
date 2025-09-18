package com.semin.watlism.data.api

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.api.dto.GenresResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbGenresApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Query("language") language: String = "ko",
    ): Response<GenresResponseDto>

    @GET("genre/tv/list")
    suspend fun getTvGenres(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Query("language") language: String = "ko",
    ): Response<GenresResponseDto>
}