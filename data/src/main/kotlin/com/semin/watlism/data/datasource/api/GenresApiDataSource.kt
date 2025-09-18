package com.semin.watlism.data.datasource.api

import com.semin.watlism.data.model.GenreData

interface GenresApiDataSource {
    suspend fun getMovieGenres(): Result<List<GenreData>>
    suspend fun getTvGenres(): Result<List<GenreData>>
}