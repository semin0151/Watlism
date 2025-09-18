package com.semin.watlism.data.model

import com.semin.watlism.domain.model.Series
import com.semin.watlism.domain.value.Rating
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.Url
import kotlinx.datetime.LocalDate

data class SeriesResponseData(
    val page: Int,
    val results: List<SeriesData>,
    val totalPages: Int,
    val totalResults: Int
)

data class SeriesData(
    val adult: Boolean,
    val backdropPath: String?,
    val genreIds: List<Int>? = null,
    val id: Long,
    val originCountry: List<String>? = null,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val posterUrl: String,
    val firstAirDate: String,
    val name: String,
    val voteAverage: Double,
    val voteCount: Int
) {
    fun toSeries() = Series(
        id = TitleId.of(id),
        name = name,
        posterUrl = Url.of(posterUrl),
        rating = Rating.of(voteAverage),
        voteCounts = voteCount,
        createdAt = LocalDate.parse(firstAirDate),
        shortDescription = overview,
        longDescription = overview,
        crews = emptyList(),
        actors = emptyList(),
    )
}