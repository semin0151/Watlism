package com.semin.watlism.domain.model

import com.semin.watlism.domain.value.Rating
import com.semin.watlism.domain.value.TitleId
import com.semin.watlism.domain.value.TitleType
import com.semin.watlism.domain.value.Url
import kotlinx.datetime.LocalDate
import kotlin.time.Duration

sealed interface Title {
    val id: TitleId
    val name: String
    val posterUrl: Url
    val rating: Rating
    val voteCounts: Int
    val createdAt: LocalDate
    val shortDescription: String
    val longDescription: String
    val crews: List<Credit>
    val actors: List<Credit>
    val type: TitleType
}

data class Movie(
    override val id: TitleId,
    override val name: String,
    override val posterUrl: Url,
    override val rating: Rating,
    override val voteCounts: Int,
    override val createdAt: LocalDate,
    override val shortDescription: String,
    override val longDescription: String,
    override val crews: List<Credit>,
    override val actors: List<Credit>,

    val runningTime: Duration,
) : Title {
    override val type: TitleType get() = TitleType.movie
}

data class Series(
    override val id: TitleId,
    override val name: String,
    override val posterUrl: Url,
    override val rating: Rating,
    override val voteCounts: Int,
    override val createdAt: LocalDate,
    override val shortDescription: String,
    override val longDescription: String,
    override val crews: List<Credit>,
    override val actors: List<Credit>,
//    val seasonCount: Int,
//    val episodeCount: Int,
//    val airingStatus: AiringStatus
) : Title {
    override val type: TitleType get() = TitleType.tv
}

enum class AiringStatus {
    OnAir, Ended
}