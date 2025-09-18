package com.semin.watlism.data.api.dto

import com.semin.watlism.data.model.TitlesResponseData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitlesResponseDto(
    val page: Int,
    val results: List<TitleDto>,
    @SerialName("total_pages")
    val totalPages: Int,
    @SerialName("total_results")
    val totalResults: Int
) {
    fun toTitlesResponseData() = TitlesResponseData(
        page = page,
        results = results.map { it.toTitleData() },
        totalPages = totalPages,
        totalResults = totalResults,
    )
}
