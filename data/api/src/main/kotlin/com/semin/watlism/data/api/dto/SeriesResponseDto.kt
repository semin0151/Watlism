package com.semin.watlism.data.api.dto

import com.semin.watlism.data.model.SeriesResponseData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesResponseDto(
    val page: Int,
    val results: List<SeriesDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    fun toSeriesResponseData() = SeriesResponseData(
        page = page,
        results = results.map { it.toSeriesData() },
        totalPages = totalPages,
        totalResults = totalResults
    )
}