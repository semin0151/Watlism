package com.semin.watlism.data.datasource.database

import com.semin.watlism.data.model.GenreData

interface GenresDatabaseDataSource {
    suspend fun upsertGenres(genres: List<GenreData>)
    suspend fun getGenres(): List<GenreData>
    suspend fun getGenreById(id: Long): GenreData?
    suspend fun getGenreByName(name: String): GenreData?
}