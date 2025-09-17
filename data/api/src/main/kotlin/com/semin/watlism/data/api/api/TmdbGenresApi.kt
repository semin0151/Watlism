package com.semin.watlism.data.api.api

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.api.dto.GenresResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface TmdbGenresApi {

    @GET("genre/movie/list")
    suspend fun getMovieGenres(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
    ): Response<GenresResponseDto>

    @GET("genre/tv/list")
    suspend fun getTvGenres(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
    ): Response<GenresResponseDto>
}