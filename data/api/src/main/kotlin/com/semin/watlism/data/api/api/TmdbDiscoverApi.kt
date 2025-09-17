package com.semin.watlism.data.api.api

import com.semin.watlism.data.api.config.ApiConfig
import com.semin.watlism.data.api.dto.MovieResponseDto
import com.semin.watlism.data.api.dto.SeriesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TmdbDiscoverApi {
    @GET("discover/movie")
    suspend fun getMovie(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Query("certification") certification: String? = null,
        @Query("certification.gte") certificationGte: String? = null,
        @Query("certification.lte") certificationLte: String? = null,
        @Query("certification_country") certificationCountry: String? = null,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_video") includeVideo: Boolean = false,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int = 1,
        @Query("primary_release_year") primaryReleaseYear: Int? = null,
        @Query("primary_release_date.gte") primaryReleaseDateGte: String? = null,
        @Query("primary_release_date.lte") primaryReleaseDateLte: String? = null,
        @Query("region") region: String? = null,
        @Query("release_date.gte") releaseDateGte: String? = null,
        @Query("release_date.lte") releaseDateLte: String? = null,
        @Query("sort_by") sortBy: String? = null,
        @Query("vote_average.gte") voteAverageGte: Double? = null,
        @Query("vote_average.lte") voteAverageLte: Double? = null,
        @Query("vote_count.gte") voteCountGte: Double? = null,
        @Query("vote_count.lte") voteCountLte: Double? = null,
        @Query("watch_region") watchRegion: String? = null,
        @Query("with_cast") withCast: String? = null,
        @Query("with_companies") withCompanies: String? = null,
        @Query("with_crew") withCrew: String? = null,
        @Query("with_genres") withGenres: String? = null,
        @Query("with_keywords") withKeywords: String? = null,
        @Query("with_origin_country") withOriginCountry: String? = null,
        @Query("with_original_language") withOriginalLanguage: String? = null,
        @Query("with_people") withPeople: String? = null,
        @Query("with_release_type") withReleaseType: String? = null,
        @Query("with_runtime.gte") withRuntimeGte: Int? = null,
        @Query("with_runtime.lte") withRuntimeLte: Int? = null,
        @Query("with_watch_monetization_types") withWatchMonetizationTypes: String? = null,
        @Query("with_watch_providers") withWatchProviders: String? = null,
        @Query("without_companies") withoutCompanies: String? = null,
        @Query("without_genres") withoutGenres: String? = null,
        @Query("without_keywords") withoutKeywords: String? = null,
        @Query("without_watch_providers") withoutWatchProviders: String? = null,
        @Query("year") year: Int? = null
    ): Response<MovieResponseDto>

    @GET("discover/tv")
    suspend fun getTv(
        @Header("Authorization") bearerToken: String = ApiConfig.BEARER_TOKEN,
        @Query("air_date.gte") airDateGte: String? = null,
        @Query("air_date.lte") airDateLte: String? = null,
        @Query("first_air_date_year") firstAirDateYear: Int? = null,
        @Query("first_air_date.gte") firstAirDateGte: String? = null,
        @Query("first_air_date.lte") firstAirDateLte: String? = null,
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("include_null_first_air_dates") includeNullFirstAirDates: Boolean = false,
        @Query("language") language: String = "ko-KR",
        @Query("page") page: Int = 1,
        @Query("screened_theatrically") screenedTheatrically: Boolean = false,
        @Query("sort_by") sortBy: String? = null,
        @Query("timezone") timezone: String? = null,
        @Query("vote_average.gte") voteAverageGte: Double? = null,
        @Query("vote_average.lte") voteAverageLte: Double? = null,
        @Query("vote_count.gte") voteCountGte: Double? = null,
        @Query("vote_count.lte") voteCountLte: Double? = null,
        @Query("watch_region") watchRegion: String? = null,
        @Query("with_companies") withCompanies: String? = null,
        @Query("with_genres") withGenres: String? = null,
        @Query("with_keywords") withKeywords: String? = null,
        @Query("with_networks") withNetworks: String? = null,
        @Query("with_origin_country") withOriginCountry: String? = null,
        @Query("with_original_language") withOriginalLanguage: String? = null,
        @Query("with_runtime.gte") withRuntimeGte: Int? = null,
        @Query("with_runtime.lte") withRuntimeLte: Int? = null,
        @Query("with_status") withStatus: String? = null,
        @Query("with_watch_monetization_types") withWatchMonetizationTypes: String? = null,
        @Query("with_watch_providers") withWatchProviders: String? = null,
        @Query("without_companies") withoutCompanies: String? = null,
        @Query("without_genres") withoutGenres: String? = null,
        @Query("without_keywords") withoutKeywords: String? = null,
        @Query("without_watch_providers") withoutWatchProviders: String? = null,
        @Query("with_type") withType: String? = null,
    ): Response<SeriesResponseDto>
}
