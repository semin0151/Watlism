package com.semin.watlism.data.api.dto

data class TitlesResponseDto(
    val page: Int,
    val results: List<TitleDto>,
    val total_pages: Int,
    val total_results: Int
)

data class TitleDto(
    val adult: Boolean,
    val backdrop_path: String?,
    val id: Int,
    val title: String?,              // 영화일 때
    val name: String?,               // 시리즈일 때
    val original_title: String?,     // 영화 원제
    val original_name: String?,      // 시리즈 원제
    val overview: String,
    val poster_path: String?,
    val media_type: String,          // "movie" or "tv"
    val original_language: String,
    val genre_ids: List<Int>,
    val popularity: Double,
    val release_date: String?,       // 영화일 때
    val first_air_date: String?,     // 시리즈일 때
    val video: Boolean?,             // 영화 전용
    val vote_average: Double,
    val vote_count: Int,
    val origin_country: List<String>? // 시리즈 전용
)