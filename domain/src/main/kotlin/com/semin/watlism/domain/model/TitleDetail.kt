package com.semin.watlism.domain.model

import com.semin.watlism.domain.value.TitleId

sealed interface TitleDetail {
    val adult: Boolean
    val backdropPath: String?
    val genres: List<Genre>
    val homepage: String
    val id: TitleId
    val originCountry: List<String>
    val originalLanguage: String
    val originalName: String
    val overview: String
    val popularity: Double
    val posterPath: String?
    val productionCompanies: List<ProductionCompany>
    val productionCountries: List<ProductionCountry>
    val spokenLanguages: List<SpokenLanguage>
    val status: String
    val tagline: String?
    val voteAverage: Double
    val voteCount: Int
}

data class MovieDetail(
    override val adult: Boolean,
    override val backdropPath: String?,
    override val genres: List<Genre>,
    override val homepage: String,
    override val id: TitleId,
    override val originCountry: List<String>,
    override val originalLanguage: String,
    override val originalName: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String?,
    override val productionCompanies: List<ProductionCompany>,
    override val productionCountries: List<ProductionCountry>,
    override val spokenLanguages: List<SpokenLanguage>,
    override val status: String,
    override val tagline: String?,
    override val voteAverage: Double,
    override val voteCount: Int,
    val belongsToCollection: CollectionInfo?,
    val budget: Int,
    val imdbId: String?,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    val title: String,
    val video: Boolean
): TitleDetail

data class SeriesDetail(
    override val adult: Boolean,
    override val backdropPath: String?,
    override val genres: List<Genre>,
    override val homepage: String,
    override val id: TitleId,
    override val originCountry: List<String>,
    override val originalLanguage: String,
    override val originalName: String,
    override val overview: String,
    override val popularity: Double,
    override val posterPath: String?,
    override val productionCompanies: List<ProductionCompany>,
    override val productionCountries: List<ProductionCountry>,
    override val spokenLanguages: List<SpokenLanguage>,
    override val status: String,
    override val tagline: String?,
    override val voteAverage: Double,
    override val voteCount: Int,
    val createdBy: List<Person>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String?,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String?,
    val lastEpisodeToAir: Episode?,
    val name: String,
    val nextEpisodeToAir: Episode?,
    val networks: List<Network>,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val seasons: List<Season>,
    val type: String,
): TitleDetail

data class ProductionCompany(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)

data class ProductionCountry(
    val iso31661: String,
    val name: String
)

data class SpokenLanguage(
    val englishName: String,
    val iso6391: String,
    val name: String
)

data class CollectionInfo(
    val id: Int,
    val name: String,
    val posterPath: String?,
    val backdropPath: String?
)

data class Episode(
    val id: Int,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val voteCount: Int,
    val airDate: String?,
    val episodeNumber: Int,
    val episodeType: String?,
    val productionCode: String?,
    val runtime: Int?,
    val seasonNumber: Int,
    val showId: Int,
    val stillPath: String?
)

data class Network(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
)

data class Season(
    val airDate: String?,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val seasonNumber: Int,
    val voteAverage: Double
)