package com.semin.watlism.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.semin.watlism.data.database.entity.GenreEntity

@Dao
interface GenreDao {

    @Upsert
    suspend fun upsertGenres(genres: List<GenreEntity>)

    @Query("SELECT * FROM genre")
    suspend fun getAllGenres(): List<GenreEntity>

    @Query("SELECT * FROM genre WHERE id = :id")
    suspend fun getGenreById(id: Long): GenreEntity?

    @Query("SELECT * FROM genre WHERE name = :name")
    suspend fun getGenreByName(name: String): GenreEntity?
}