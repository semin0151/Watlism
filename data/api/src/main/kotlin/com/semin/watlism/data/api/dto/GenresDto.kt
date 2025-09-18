package com.semin.watlism.data.api.dto

import com.semin.watlism.data.model.GenreData
import kotlinx.serialization.Serializable

@Serializable
data class GenreDto(
    val id: Long,
    val name: String,
) {
    fun toGenreData() = GenreData(
        id = id,
        name = name,
    )
}
