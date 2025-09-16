package com.semin.watlism.data.api.dto

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.datasource.api.model.MediaType
import com.semin.watlism.data.datasource.api.model.TitleData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TitleDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    val id: Long,
    val title: String? = null,
    val name: String? = null,
    @SerialName("original_title")
    val originalTitle: String? = null,
    @SerialName("original_name")
    val originalName: String? = null,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("media_type")
    val mediaType: String,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val popularity: Double,
    @SerialName("release_date")
    val releaseDate: String? = null,
    @SerialName("first_air_date")
    val firstAirDate: String? = null,
    val video: Boolean? = null,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("origin_country")
    val originCountry: List<String>? = null
) {
    val backdropUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${backdropPath}"
    val posterUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${posterPath}"

    fun toTitleData(): TitleData {
        return TitleData(
            adult = adult,
            backdropPath = backdropPath,
            backdropUrl = backdropUrl,
            id = id,
            title = title,
            name = name,
            originalTitle = originalTitle,
            originalName = originalName,
            overview = overview,
            posterPath = posterPath,
            posterUrl = posterUrl,
            mediaType = MediaType.valueOf(mediaType),
            originalLanguage = originalLanguage,
            genreIds = genreIds,
            popularity = popularity,
            releaseDate = releaseDate,
            firstAirDate = firstAirDate,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
            originCountry = originCountry,
        )
    }
}