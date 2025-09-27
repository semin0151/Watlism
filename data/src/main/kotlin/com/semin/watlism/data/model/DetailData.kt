package com.semin.watlism.data.model

import com.semin.watlism.domain.model.CollectionInfo
import com.semin.watlism.domain.model.Episode
import com.semin.watlism.domain.model.MovieDetail
import com.semin.watlism.domain.model.Network
import com.semin.watlism.domain.model.Person
import com.semin.watlism.domain.model.ProductionCompany
import com.semin.watlism.domain.model.ProductionCountry
import com.semin.watlism.domain.model.Season
import com.semin.watlism.domain.model.SeriesDetail
import com.semin.watlism.domain.model.SpokenLanguage
import com.semin.watlism.domain.value.PersonId
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.Url

data class MovieDetailData(
    val adult: Boolean,
    val backdropPath: String?,
    val backdropUrl: String,
    val belongsToCollection: CollectionInfoData?,
    val budget: Int,
    val genres: List<GenreData>,
    val homepage: String,
    val id: Long,
    val imdbId: String?,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val posterUrl: String,
    val productionCompanies: List<ProductionCompanyData>,
    val productionCountries: List<ProductionCountryData>,
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguageData>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
) {
    fun toMovieDetail() = MovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        backdropUrl = backdropUrl,
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = TitleId.of(id),
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        posterUrl = posterUrl,
        productionCompanies = productionCompanies.map { it.toProductionCompany() },
        productionCountries = productionCountries.map { it.toProductionCountry() },
        spokenLanguages = spokenLanguages.map { it.toSpokenLanguage() },
        status = status,
        tagline = tagline,
        voteAverage = voteAverage,
        voteCount = voteCount,
        belongsToCollection = belongsToCollection?.toCollectionInfo(),
        budget = budget,
        imdbId = imdbId,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        name = title,
        video = video
    )
}

data class SeriesDetailData(
    val adult: Boolean,
    val backdropPath: String?,
    val backdropUrl: String,
    val createdBy: List<CreatorData>,
    val episodeRunTime: List<Int>,
    val firstAirDate: String?,
    val genres: List<GenreData>,
    val homepage: String,
    val id: Long,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String?,
    val lastEpisodeToAir: EpisodeData?,
    val name: String,
    val nextEpisodeToAir: EpisodeData?,
    val networks: List<NetworkData>,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val posterUrl: String,
    val productionCompanies: List<ProductionCompanyData>,
    val productionCountries: List<ProductionCountryData>,
    val seasons: List<SeasonData>,
    val spokenLanguages: List<SpokenLanguageData>,
    val status: String,
    val tagline: String?,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int
) {
    fun toSeriesDetail() = SeriesDetail(
        adult = adult,
        backdropPath = backdropPath,
        backdropUrl = backdropUrl,
        createdBy = createdBy.map { it.toPerson() },
        episodeRunTime = episodeRunTime,
        releaseDate = firstAirDate ?: "",
        genres = genres.map { it.toGenre() },
        homepage = homepage,
        id = TitleId.of(id),
        inProduction = inProduction,
        languages = languages,
        lastAirDate = lastAirDate,
        lastEpisodeToAir = lastEpisodeToAir?.toEpisode(),
        name = name,
        nextEpisodeToAir = nextEpisodeToAir?.toEpisode(),
        networks = networks.map { it.toNetwork() },
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        posterUrl = posterUrl,
        productionCompanies = productionCompanies.map { it.toProductionCompany() },
        productionCountries = productionCountries.map { it.toProductionCountry() },
        seasons = seasons.map { it.toSeason() },
        spokenLanguages = spokenLanguages.map { it.toSpokenLanguage() },
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

data class ProductionCompanyData(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
) {
    fun toProductionCompany() = ProductionCompany(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}

data class ProductionCountryData(
    val iso31661: String,
    val name: String
) {
    fun toProductionCountry() = ProductionCountry(
        iso31661 = iso31661,
        name = name
    )
}

data class SpokenLanguageData(
    val englishName: String,
    val iso6391: String,
    val name: String
) {
    fun toSpokenLanguage() = SpokenLanguage(
        englishName = englishName,
        iso6391 = iso6391,
        name = name
    )
}

data class CollectionInfoData(
    val id: Int,
    val name: String,
    val posterPath: String?,
    val backdropPath: String?
) {
    fun toCollectionInfo() = CollectionInfo(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath
    )
}

data class CreatorData(
    val id: Long,
    val name: String,
    val profilePath: String? = null
) {
    fun toPerson() = Person(
        id = PersonId.of(id),
        name = name,
        profileUrl = Url.of(profilePath ?: "")
    )
}

data class EpisodeData(
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
) {
    fun toEpisode() = Episode(
        id = id,
        name = name,
        overview = overview,
        voteAverage = voteAverage,
        voteCount = voteCount,
        airDate = airDate,
        episodeNumber = episodeNumber,
        episodeType = episodeType,
        productionCode = productionCode,
        runtime = runtime,
        seasonNumber = seasonNumber,
        showId = showId,
        stillPath = stillPath
    )
}

data class NetworkData(
    val id: Int,
    val logoPath: String?,
    val name: String,
    val originCountry: String
) {
    fun toNetwork() = Network(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}

data class SeasonData(
    val airDate: String?,
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String?,
    val seasonNumber: Int,
    val voteAverage: Double
) {
    fun toSeason() = Season(
        airDate = airDate,
        episodeCount = episodeCount,
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        seasonNumber = seasonNumber,
        voteAverage = voteAverage
    )
}