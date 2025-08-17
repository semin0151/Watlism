package com.semin.watlism.domain.model

sealed interface Title {
    val id: Long
    val name: String
    val posterUrl: String
    val rating: Double
    val createdAt: Long
    val shortDescription: String
    val longDescription: String
    val crews: List<Person.Crew>
    val actors: List<Person.Actor>

    data class Movie(
        override val id: Long,
        override val name: String,
        override val posterUrl: String,
        override val rating: Double,
        override val createdAt: Long,
        override val shortDescription: String,
        override val longDescription: String,
        override val crews: List<Person.Crew>,
        override val actors: List<Person.Actor>,
        val runningTime: Long
    ) : Title

    data class Series(
        override val id: Long,
        override val name: String,
        override val posterUrl: String,
        override val rating: Double,
        override val createdAt: Long,
        override val shortDescription: String,
        override val longDescription: String,
        override val crews: List<Person.Crew>,
        override val actors: List<Person.Actor>,
        val seasonCount: Int,
        val episodeCount: Int,
        val airingStatus: AiringStatus
    ) : Title
}

enum class AiringStatus {
    OnAir, Ended
}