package com.semin.watlism.data.datasource.api.model

import com.semin.watlism.domain.model.AiringStatus
import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.model.Title
import com.semin.watlism.domain.value.Rating
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.Url
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.time.Duration

data class TitlesResponseData(
    val page: Int,
    val results: List<TitleData>,
    val totalPages: Int,
    val totalResults: Int
)

@Serializable
data class TitleData(
    val adult: Boolean,
    val backdropPath: String? = null,
    val backdropUrl: String,
    val id: Long,
    val title: String? = null,
    val name: String? = null,
    val originalTitle: String? = null,
    val originalName: String? = null,
    val overview: String,
    val posterPath: String? = null,
    val posterUrl: String,
    val mediaType: MediaType,
    val originalLanguage: String,
    val genreIds: List<Int>,
    val popularity: Double,
    val releaseDate: String? = null,
    val firstAirDate: String? = null,
    val video: Boolean? = null,
    val voteAverage: Double,
    val voteCount: Int,
    val originCountry: List<String>? = null
) {
    fun toTitle(): Title? {
        return when (mediaType) {
            MediaType.movie -> {
                Movie(
                    id = TitleId.of(id),
                    name = title ?: "",
                    posterUrl = Url.of(posterUrl),
                    rating = Rating.of(voteAverage),
                    voteCounts = voteCount,
                    createdAt = LocalDate.parse(releaseDate ?: ""),
                    shortDescription = overview,
                    longDescription = overview,
                    crews = emptyList(),
                    actors = emptyList(),
                    runningTime = Duration.ZERO,
                )
            }

            MediaType.tv -> {
                Series(
                    id = TitleId.of(id),
                    name = name ?: "",
                    posterUrl = Url.of(posterUrl),
                    rating = Rating.of(voteAverage),
                    voteCounts = voteCount,
                    createdAt = LocalDate.parse(firstAirDate ?: ""),
                    shortDescription = overview,
                    longDescription = overview,
                    crews = emptyList(),
                    actors = emptyList(),
                    seasonCount = 0,
                    episodeCount = 0,
                    airingStatus = AiringStatus.OnAir,
                )
            }

            MediaType.person -> null
        }
    }
}

enum class MediaType {
    movie, tv, person
}