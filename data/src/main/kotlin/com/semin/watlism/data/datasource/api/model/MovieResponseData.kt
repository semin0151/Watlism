package com.semin.watlism.data.datasource.api.model

import com.semin.watlism.domain.model.Movie
import com.semin.watlism.domain.value.Rating
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.Url
import kotlinx.datetime.LocalDate
import kotlin.time.Duration

data class MovieResponseData(
    val page: Int,
    val results: List<MovieData>,
    val totalPages: Int,
    val totalResults: Int
)

data class MovieData(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>? = null,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val posterUrl: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
) {
    fun toMovie() = Movie(
        id = TitleId.of(id),
        name = title,
        posterUrl = Url.of(posterUrl),
        rating = Rating.of(voteAverage),
        voteCounts = voteCount,
        createdAt = LocalDate.parse(releaseDate),
        shortDescription = overview,
        longDescription = overview,
        crews = emptyList(),
        actors = emptyList(),
        runningTime = Duration.ZERO
    )
}