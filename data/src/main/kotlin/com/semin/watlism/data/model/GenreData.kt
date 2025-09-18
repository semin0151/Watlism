package com.semin.watlism.data.model

import com.semin.watlism.domain.model.Genre

data class GenreData(
    val id: Long,
    val name: String,
) {
    fun toGenre() = Genre(
        id = id,
        name = name
    )
}
