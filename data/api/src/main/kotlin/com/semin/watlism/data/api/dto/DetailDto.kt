package com.semin.watlism.data.api.dto

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.model.CollectionInfoData
import com.semin.watlism.data.model.CreatorData
import com.semin.watlism.data.model.EpisodeData
import com.semin.watlism.data.model.MovieDetailData
import com.semin.watlism.data.model.NetworkData
import com.semin.watlism.data.model.ProductionCompanyData
import com.semin.watlism.data.model.ProductionCountryData
import com.semin.watlism.data.model.SeasonData
import com.semin.watlism.data.model.SeriesDetailData
import com.semin.watlism.data.model.SpokenLanguageData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("belongs_to_collection")
    val belongsToCollection: CollectionInfoDto?,
    val budget: Int,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Long,
    @SerialName("imdb_id")
    val imdbId: String?,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto>,
    @SerialName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int?,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    val backdropUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${backdropPath}"
    val posterUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${posterPath}"

    fun toMovieDetailData() = MovieDetailData(
        adult = adult,
        backdropPath = backdropPath,
        backdropUrl = backdropUrl,
        belongsToCollection = belongsToCollection?.toCollectionInfoData(),
        budget = budget,
        genres = genres.map { it.toGenreData() },
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        posterUrl = posterUrl,
        productionCompanies = productionCompanies.map { it.toProductionCompanyData() },
        productionCountries = productionCountries.map { it.toProductionCountryData() },
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.toSpokenLanguageData() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

@Serializable
data class SeriesDetailDto(
    val adult: Boolean,
    @SerialName("backdrop_path")
    val backdropPath: String?,
    @SerialName("created_by")
    val createdBy: List<CreatorDto>,
    @SerialName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerialName("first_air_date")
    val firstAirDate: String?,
    val genres: List<GenreDto>,
    val homepage: String,
    val id: Long,
    @SerialName("in_production")
    val inProduction: Boolean,
    val languages: List<String>,
    @SerialName("last_air_date")
    val lastAirDate: String?,
    @SerialName("last_episode_to_air")
    val lastEpisodeToAir: EpisodeDto?,
    val name: String,
    @SerialName("next_episode_to_air")
    val nextEpisodeToAir: EpisodeDto?,
    val networks: List<NetworkDto>,
    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,
    @SerialName("number_of_seasons")
    val numberOfSeasons: Int,
    @SerialName("origin_country")
    val originCountry: List<String>,
    @SerialName("original_language")
    val originalLanguage: String,
    @SerialName("original_name")
    val originalName: String,
    val overview: String,
    val popularity: Double,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompanyDto>,
    @SerialName("production_countries")
    val productionCountries: List<ProductionCountryDto>,
    val seasons: List<SeasonDto>,
    @SerialName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto>,
    val status: String,
    val tagline: String?,
    val type: String,
    @SerialName("vote_average")
    val voteAverage: Double,
    @SerialName("vote_count")
    val voteCount: Int
) {
    val backdropUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${backdropPath}"
    val posterUrl = "${ApiConfig.TMDB_SAMPLING_IMAGE_URL}${posterPath}"

    fun toSeriesDetailData() = SeriesDetailData(
        adult = adult,
        backdropPath = backdropPath,
        backdropUrl = backdropUrl,
        createdBy = createdBy.map { it.toCreatorData() },
        episodeRunTime = episodeRunTime,
        firstAirDate = firstAirDate,
        genres = genres.map { it.toGenreData() },
        homepage = homepage,
        id = id,
        inProduction = inProduction,
        languages = languages,
        lastAirDate = lastAirDate,
        lastEpisodeToAir = lastEpisodeToAir?.toEpisodeData(),
        name = name,
        nextEpisodeToAir = nextEpisodeToAir?.toEpisodeData(),
        networks = networks.map { it.toNetworkData() },
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        posterUrl = posterUrl,
        productionCompanies = productionCompanies.map { it.toProductionCompanyData() },
        productionCountries = productionCountries.map { it.toProductionCountryData() },
        seasons = seasons.map { it.toSeasonData() },
        spokenLanguages = spokenLanguages.map { it.toSpokenLanguageData() },
        status = status,
        tagline = tagline,
        type = type,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

@Serializable
data class ProductionCompanyDto(
    val id: Int,
    @SerialName("logo_path")
    val logoPath: String?,
    val name: String,
    @SerialName("origin_country")
    val originCountry: String
) {
    fun toProductionCompanyData() = ProductionCompanyData(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}

@Serializable
data class ProductionCountryDto(
    @SerialName("iso_3166_1")
    val iso31661: String,
    val name: String
) {
    fun toProductionCountryData() = ProductionCountryData(
        iso31661 = iso31661,
        name = name
    )
}

@Serializable
data class SpokenLanguageDto(
    @SerialName("english_name")
    val englishName: String,
    @SerialName("iso_639_1")
    val iso6391: String,
    val name: String
) {
    fun toSpokenLanguageData() = SpokenLanguageData(
        englishName = englishName,
        iso6391 = iso6391,
        name = name
    )
}

@Serializable
data class CollectionInfoDto(
    val id: Int,
    val name: String,
    @SerialName("poster_path")
    val posterPath: String?,
    @SerialName("backdrop_path")
    val backdropPath: String?
) {
    fun toCollectionInfoData() = CollectionInfoData(
        id = id,
        name = name,
        posterPath = posterPath,
        backdropPath = backdropPath
    )
}

@Serializable
data class CreatorDto(
    val id: Long,
    val name: String,
    @SerialName("profile_path") val profilePath: String? = null
) {
    fun toCreatorData() = CreatorData(
        id = id,
        name = name,
        profilePath = profilePath
    )
}

@Serializable
data class EpisodeDto(
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("air_date") val airDate: String?,
    @SerialName("episode_number") val episodeNumber: Int,
    @SerialName("episode_type") val episodeType: String?,
    @SerialName("production_code") val productionCode: String?,
    val runtime: Int?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("show_id") val showId: Int,
    @SerialName("still_path") val stillPath: String?
) {
    fun toEpisodeData() = EpisodeData(
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

@Serializable
data class NetworkDto(
    val id: Int,
    @SerialName("logo_path") val logoPath: String?,
    val name: String,
    @SerialName("origin_country") val originCountry: String
) {
    fun toNetworkData() = NetworkData(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}

@Serializable
data class SeasonDto(
    @SerialName("air_date") val airDate: String?,
    @SerialName("episode_count") val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @SerialName("poster_path") val posterPath: String?,
    @SerialName("season_number") val seasonNumber: Int,
    @SerialName("vote_average") val voteAverage: Double
) {
    fun toSeasonData() = SeasonData(
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