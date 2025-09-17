package com.semin.watlism.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class GenresResponseDto(
    val genres: List<GenreDto>
)
