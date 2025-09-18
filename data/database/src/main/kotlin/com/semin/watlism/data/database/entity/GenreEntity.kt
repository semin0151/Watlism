package com.semin.watlism.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.semin.watlism.data.database.GenreDatabase
import com.semin.watlism.data.model.GenreData

@Entity(tableName = GenreDatabase.GENRE_TABLE_NAME)
data class GenreEntity(
    @PrimaryKey val id: Long,
    val name: String
) {
    companion object {
        fun toGenreEntity(genreData: GenreData) = GenreEntity(
            id = genreData.id,
            name = genreData.name
        )
    }

    fun toGenreData() = GenreData(
        id = id,
        name = name
    )
}
