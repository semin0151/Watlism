package com.semin.watlism.data.api.dto

import com.semin.watlism.data.model.MovieResponseData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponseDto(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<MovieDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    fun toMovieResponseData() = MovieResponseData(
        page = page,
        results = results.map { it.toMovieData() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}