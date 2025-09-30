package com.semin.watlism.data.api.dto

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.model.CreditsData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCreditsDto(
    val id: Long,
    val cast: List<CreditDto>
)

@Serializable
data class SeriesCreditsDto(
    val cast: List<CreditDto>
)

@Serializable
data class CreditDto(
    val adult: Boolean,
    val gender: Int? = null,
    val id: Long,
    @SerialName("known_for_department")
    val knownForDepartment: String,
    val name: String,
    @SerialName("original_name")
    val originalName: String,
    val popularity: Double,
    @SerialName("profile_path")
    val profilePath: String? = null,
    @SerialName("cast_id")
    val castId: Int? = null,
    val character: String,
    @SerialName("credit_id")
    val creditId: String,
    val order: Int
) {
    val profileUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${profilePath}"

    fun toCreditData() = CreditsData(
        adult = adult,
        gender = gender,
        personId = id,
        knownForDepartment = knownForDepartment,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profileUrl = profileUrl,
        castId = castId,
        character = character,
        creditId = creditId,
        order = order
    )
}