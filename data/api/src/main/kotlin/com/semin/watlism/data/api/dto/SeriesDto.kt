package com.semin.watlism.data.api.dto

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.model.SeriesData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val id: Long,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("first_air_date")
    val firstAirDate: String,
    val name: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    val backdropUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${backdropPath}"
    val posterUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${posterPath}"

    fun toSeriesData() = SeriesData(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        posterUrl = posterUrl,
        firstAirDate = firstAirDate,
        name = name,
        voteAverage = voteAverage,
        voteCount = voteCount,
    )
}
