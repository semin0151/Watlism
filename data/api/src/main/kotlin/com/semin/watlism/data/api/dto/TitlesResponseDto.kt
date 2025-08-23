package com.semin.watlism.data.api.dto

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.datasource.api.model.MediaType
import com.semin.watlism.data.datasource.api.model.TitleData
import com.semin.watlism.data.datasource.api.model.TitlesResponseData
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

@Serializable
data class TitleDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String? = null,
    val id: Long,
    val title: String? = null,              // 영화일 때
    val name: String? = null,               // 시리즈일 때
    @SerialName("original_title")
    val originalTitle: String? = null,      // 영화 원제
    @SerialName("original_name")
    val originalName: String? = null,       // 시리즈 원제
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    @SerialName("media_type")
    val mediaType: String,           // "movie" or "tv"
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("genre_ids")
    val genreIds: List<Int>,
    val popularity: Double,
    @SerialName("release_date")
    val releaseDate: String? = null,        // 영화일 때
    @SerialName("first_air_date")
    val firstAirDate: String? = null,       // 시리즈일 때
    val video: Boolean? = null,             // 영화 전용
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("origin_country")
    val originCountry: List<String>? = null // 시리즈 전용
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